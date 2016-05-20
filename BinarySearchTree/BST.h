#ifndef BST_H
#define BST_H

using namespace std;

class BST {
   public:
      // Constructors
      BST();
      BST(int value); // Construct an instance of a BST
                              //   setting value field to value

      // Methods associated with tree object
      bool isEmpty();
      bool insert(int value);
      bool find(int value);
      void preorder();
      void inorder();
      void postorder();

   private:
      //  Data associated with a tree object
      int key;            // Node value
      BST *left;         // Pointer to left subtree
      BST *right;        // Pointer to right subtree
};

#endif
