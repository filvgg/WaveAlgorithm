# Test  version

I'm using algorithm of wave tracing (wave algorithm, Lee algorithm) - path search algorithm, 
shortest path search algorithm on a planar graph:

1) Initialization
 - Select start point, mark with 0
 - i := 1
2) Wave expansion
 - REPEAT
     - Mark all unlabeled neighbors of points marked with i with i+1
     - i := i+1
   UNTIL ((target reached) or (no points can be marked))
 

Wave Expansion step
3) Backtrace
   - go to the target point
   REPEAT
     - go to next node that has a lower mark than the current node
     - add this node to path
   UNTIL (start point reached)
--------------------------------------------------------------

The program reads a hardcoded file : matr.txt which should be in the program directory.
The matr.txt file must contain the initialization map.







   