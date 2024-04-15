package com.joe.wordGraph.service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.wordGraph.dao.UserBookRepository;
import com.joe.wordGraph.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;


@Service
public class UserBookServiceImpl implements UserBookService {

    private UserBookRepository userBookRepository;
    private UserWord word;
    private String graphGalleryPath = "C:\\Projects\\WordGraphs\\PhotoGallery\\";
    @Autowired
    public UserBookServiceImpl(UserBookRepository theUserBookRepository) {
        userBookRepository = theUserBookRepository;
    }

    @Override
    public List<Users_Books> findAll() {
        return userBookRepository.findAll();
    }

    @Override
    public Users_Books findById(Users_Books.UserBookId theId) {
        Optional<Users_Books> result = userBookRepository.findById(theId);

        Users_Books theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the userBook
            throw new RuntimeException("Did not find user_book by id - " + theId);
        }

        return theUser;
    }

    @Override
    public void save(Users_Books theUserBook) {
        userBookRepository.save(theUserBook);
    }

    @Override
    public void deleteById(Users_Books.UserBookId theId) {
        userBookRepository.deleteById(theId);
    }

    @Override
    public void setUserWordList(UserWordList theUserWordList, Users_Books theUserBook) throws IllegalAccessException {
        List<UserWord> wordList = theUserWordList.getWords();
        JSONArray jsonArr = new JSONArray();
        // reflect
        UserWord myObject = new UserWord();
        Class<?> clazz = myObject.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (UserWord word: wordList) {
                JSONObject jsonObj = new JSONObject();
                for (Field field : fields) {
                    field.setAccessible(true); // 设置字段可访问
                    Object value = field.get(word); // 获取字段的值
                    jsonObj.put(String.valueOf(field.getName()), value);
                }
                jsonArr.put(jsonObj);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String jsonString = JSON.toJSONString(jsonArr);
        theUserBook.setUserWordList(jsonString);
        userBookRepository.save(theUserBook);
    }

    @Override
    public UserWordList getUserWordList(int userId, int bookId) {
        Users_Books usersBooks = findById(new Users_Books.UserBookId(userId, bookId));
        UserWordList theUserWordList = new UserWordList();

        if (usersBooks != null) {
            String userWordList = usersBooks.getUserWordList();
            theUserWordList.setWords(createUserWordList(userWordList));
        } else {
            // we didn't find the userBook
            throw new RuntimeException("Did not find user_book by id - " + userId + "&" +  bookId);
        }
        return theUserWordList;
    }

    // convert JSON string to List<UserWord>
    public List<UserWord> createUserWordList(String userWordListStr) {

        List<UserWord> userWordList;
        Type listType = new TypeToken<List<UserWord>>() {}.getType();
        userWordList = new Gson().fromJson(userWordListStr, listType);
        return userWordList;

    }


    @Override
    public List<Graph> getNineGraph(UserWord userWord) {

        List<Graph> graphs = new ArrayList<>();
        String wordFileName = userWord.getWord() + ".jpg";
        File folder = new File(graphGalleryPath);
        File[] files = folder.listFiles();

        Random random = new Random();

        Set<String> selectedFiles = new HashSet<>();
        assert files != null;
        int numFiles = files.length;
        selectedFiles.add(wordFileName);
        int numSelected = 8;
        while (selectedFiles.size() < numSelected) {
            int randomIndex = random.nextInt(numFiles);
            String fileName = files[randomIndex].getName();
            selectedFiles.add(fileName);
        }


        for (String file: selectedFiles) {
            int len = file.length();
            String name = file.substring(0, len-4);
//            String addr = graphGalleryPath + file;
//            System.out.println(file);
            graphs.add(new Graph(name, file));
        }

        return graphs;
    }

    // convert JSON string to List<UserWord>
    @Override
    public UserWordList buildUserWordList(WordList theWordList) {
        UserWordList theUserWordList = new UserWordList();
        for (var str: theWordList.getWordsAndOcc()) {
            String[] strs = str.split(":");
            String word = strs[0];
            int times = Integer.valueOf(strs[1]);
            UserWord userWord = new UserWord(word, times);
            theUserWordList.addWord(userWord);
        }
        return theUserWordList;
    }

    // to get 9 graphs
    @Override
    public List<Graph> getGraphs(int userId, int bookId) {
        UserWordList theUserWordList = getUserWordList(userId, bookId);
        UserWord theUserWord = getPriorityWord(theUserWordList);
        return getNineGraph(theUserWord);
    }

    private UserWord getPriorityWord(UserWordList theUserWordList) {
        MemoryCompare memoryCompare = new MemoryCompare();
        Collections.sort(theUserWordList.getWords(), memoryCompare);
        return theUserWordList.getWords().get(0);
    }

    // Class to compare Movies by name
    static class MemoryCompare implements Comparator<UserWord> {
        @Override
        public int compare(UserWord w1, UserWord w2) {
            if (!w1.isIs_memory() && !w2.isIs_memory()) {
                // todo: more complex compare rule
                if (w1.getOccurence_freqence() >= w2.getOccurence_freqence()) {
                    return -1;
                } else {
                    return 1;
                }
            }
            else if (!w1.isIs_memory()) {
                return -1;
            } else if (!w2.isIs_memory()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
