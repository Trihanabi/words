# word-image
#### 背景
这个程序从帮助人们阅读英文书籍的期望出发，通过将一本英文书籍中出现频率最高的非容易词（去除2000常用词）整理成单词列表，再将列表中的单词转换为图像，通过在类似九宫格的图像中选择单词来记忆单词。本程序也用于本人练习spring boot, mysql和vue的目的，目前已经实现了基本的功能。 

#### 程序先前计划
![alt text](page-flow4.svg)

#### 文件结构  
main   
├── java.com.joe.wordImage  
&thinsp;|&emsp;&emsp;├── book_word_list: 各书高频非容易词列表  
&thinsp;|&emsp;&emsp;├── controller: 控制器  
&thinsp;|&emsp;&emsp;├── dao: 数据访问层  
&thinsp;|&emsp;&emsp;├── entity: 实体类  
&thinsp;|&emsp;&emsp;├── helper  
&thinsp;|&emsp;&emsp;&thinsp;|&emsp;&emsp;├── CreateBooks.java: 构建书籍词汇列表  
&thinsp;|&emsp;&emsp;&thinsp;|&emsp;&emsp;└── CreateMockUser.java: 创建虚拟用户（用户功能为下一步计划）  
&thinsp;|&emsp;&emsp;├── service: 服务层  
&thinsp;|&emsp;&emsp;└── JoeApplication: 应用程序  
└── resources  
&thinsp;&thinsp;&emsp;&emsp;├── static.images: webp格式的图片库  
&thinsp;&thinsp;&emsp;&emsp;└── templates  
&thinsp;&thinsp;&thinsp;&emsp;&emsp;&emsp;&emsp;├── books  
&thinsp;&thinsp;&thinsp;&emsp;&emsp;&emsp;&emsp;&thinsp;|&emsp;&emsp;├── book_words.html: 书籍单词选择页面  
&thinsp;&thinsp;&thinsp;&emsp;&emsp;&emsp;&emsp;&thinsp;|&emsp;&emsp;├── list_books.html: 书籍列表页面   
&thinsp;&thinsp;&thinsp;&emsp;&emsp;&emsp;&emsp;&thinsp;|&emsp;&emsp;└── submit-words.html: 提交单词页面  
&thinsp;&thinsp;&thinsp;&emsp;&emsp;&emsp;&emsp;└── memory  
&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;└── word_memory.html: 记忆单词页面（包含操作单词列表的逻辑）  


#### 目标
1. 当下目标：帮助人们记忆单词，以能阅读简单英文书籍  
2. 下一步：帮助人们分析文本或者书籍，提取总结非容易词  
3. 下一步：用户自我构建单词列表并能分享

#### 下一步中需考虑的衡量因素
1. 它能帮助人记忆单词：   
&nbsp;&nbsp;&nbsp;&nbsp; 1.1 短期记忆：10分钟内能记忆的单词数   
&nbsp;&nbsp;&nbsp;&nbsp; 1.2 长时间记忆：能牢记和快速反应的单词数  
2. 它能帮助人开始阅读一本英文书   
&nbsp;&nbsp;&nbsp;&nbsp; 2.1 使用后对人们阅读不同难度书籍停顿次数的减少  

#### Plan & Next steps  
- [ ] 1. 增加用户功能    
- [ ] 2. 完善图片集   
- [ ] 3. 完善用户记忆过程                     
&nbsp;&nbsp;&nbsp;&nbsp; 3.1 完善单词列表的排序，让它更符合记忆规律     
&nbsp;&nbsp;&nbsp;&nbsp; 3.2 完善记忆积分，提供即时的反馈    
- [ ] 4. 增加分析上传文本功能     


#### 问题
