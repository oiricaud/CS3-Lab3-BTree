Description:
Recall that a B-tree is a rooted tree having the following properties: 

1. Every node x has the following attributes:
(a) x.n, the number of keys currently stored in node x,
(b) the x.n keys themselves, x.key1, x.key2, . . . , x.keyx.n , stored in nondecreasing order.
(c) x.leaf , a boolean value that is TRUE if x is a leaf and FALSE if x is an internal node.

2. Each internal node x contains also x.n + 1 pointers x.c1, . . . , x.cx.n+1 to its children. Leaf nodes have no
children, and so their attributes ci are undefined.

3. The keys x.keyi separate the ranges of keys stored in each subtree.

4. All leaves have the same depth, which is the tree’s height.

5. Nodes have lower and upper bounds on the number of keys they can contain. 
We express these bounds in terms of a fixed integer t ≥ 2 called the minimum degree of the B-tree:
(a) Every node other than the root must have at least t − 1 keys. Every internal node other than the root
thus has at least t children. If the tree is nonempty, the root must have at least one key.
(b) Every node may contain at most 2t − 1 keys. Therefore, an internal node may have at most 2t children.
We say that a node is full if it contains exactly 2t − 1 keys.

Problem: Create a class named BTree that implements the following methods:

1. Create an empty tree.
2. Create a tree from of an array.
3. Insert a new key into a tree.
4. Delete a key from a tree.
5. Print the keys in the tree in descending order. 6. Determine if a given element k is in the tree. 7. Return the minimum element in the tree.
8. Return the maximum element in the tree.
9. Return the number of nodes in the tree.
10. Return the number of keys in the tree.
11. Return the sum of all the keys in the tree.
12. Return the number of leaves in the tree.
13. Return the number of nodes in the tree that are full.
14. Return the number of nodes in the tree that have the minimum number of keys.
15. Given an integer n, return the number of nodes in the tree that have exactly n keys. 16. Given an integer d, 
return the number of nodes in the tree that have depth d.

Write a report describing your work.
