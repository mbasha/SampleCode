#include <iostream>
#include <stack>
#include "BST.h"

using namespace std;

// Name:		        Abrar Basha, Spring 2013
//                      Lab #4, BST

// Header Comment: Creation of a binary search tree. Checks for an empty tree, inserts into tree, finds from a tree, and sorts into preorder, postorder, or inorder.


//creation of tree
BST::BST(){
   key = -9999999;
   left = NULL;
   right = NULL;
}

BST::BST(int value){
   key = value;
   left = NULL;
   right = NULL;
}

//checks if tree is empty
bool BST::isEmpty(){
   if(key == -9999999)
   {
      if (left == NULL){
         if(right == NULL){
            return true;
         }
         return false;
      }
   }//if
   return false;
}//isempty

//inserts into tree
bool BST::insert (int value){
   if(key == -9999999)
   {
   	key = value;
   	return true;
   }//if
   
   else if(key == value)
   {
      return false;
   }//else if
   
   else if(key > value){
      if (left != NULL)
      {
         return(left->insert(value));
      }
      else{
         left = new BST(value);
         return true;
      }
   }//else if
   
   else{
      if(right != NULL)
      {
         return(right->insert(value));
      }
      else{
         right = new BST(value);
         return true;
      }
   }//else
}//insert

//finds value in the tree
bool BST::find (int value){
   if(key == value)
   {
      return true;
   }//if
   else if(key > value)
   {
      if(left != NULL)
      {
         return(left->find(value));
      }
   }//else if
   else{
      if(right != NULL)
      {
         return(right->find(value));
      }
      return false;
   }//else
}//find

//prints out tree is preorder
void BST::preorder(){
   if(this != NULL)
   {
      cout<< key <<" ";
      left->inorder();
      right->inorder();
   }
}//preorder

//prints out tree in inorder
void BST::inorder(){
   if(this != NULL)
   {
      left->inorder();
      cout<<key<<" ";
      right->inorder();
   }
}//inorder

//prints out tree in postorder
void BST::postorder(){
   if(this != NULL)
   {
      left->inorder();
      right->inorder();
      cout<<key<<" ";
   }
}//postorder

