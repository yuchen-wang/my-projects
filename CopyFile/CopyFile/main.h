//
//  main.h
//  CopyFile
//
//  Created by Yuchen Wang on 12-04-28.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#ifndef CopyFile_main_h
#define CopyFile_main_h

struct LinkedList
{
    int id;
    struct LinkedList* next;
};
typedef struct LinkedList Node;

void* work(void* argument);

#endif
