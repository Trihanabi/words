#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define LENGTH 45
int main(int argc, char*argv[])
{
    char str[LENGTH];
    int n = 0;
    // keep argc = 2;
    if (argc != 2)
    {
        printf("Please input as \"./modify texts/file.txt \"");
    }

    // read dictionary file and open a temp file as write mode
    char *dictionary = argv[1];
    FILE *fp, *fout;
    fp = fopen(dictionary, "r");

    // build new file name
    char* dest = strtok(dictionary, ".");
    char* suffix = "_word.txt";
    strcat(dest, suffix);

    // printf("%s", dest);
    fout = fopen(dest, "w");

    // if file is empty, return false
    if (fp == NULL)
    {
        puts("file open failed");
        return (-1);
    }

    // if output file out fail return false
    if (fout == NULL)
    {
        puts("New dictionary file open failed");
        return (-1);
    }

    // read file as line by line
    while (!feof(fp))
    {
        fgets(str, LENGTH, fp);
        // write line begin with "+" in tmp.txt
        if (str[0] == '+') {
            int len = strlen(str);
            for (int i = 0; i < len-3; i++)
            {
                str[i] = str[i+1];
            }
            str[len - 3] = '\0';
            fprintf(fout, "%s\n", str);
            continue;
        }
    }

    // close points of files and delete the original file and rename tmp file
    fclose(fp);
    fclose(fout);
    // remove(dictionary);
    // rename("tmp.txt", "new.txt");
    return 0;
}