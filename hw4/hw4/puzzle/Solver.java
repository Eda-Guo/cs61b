package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;


public class Solver {
    private MinPQ<Node> pq;
    private int moves;
    private List<Node> solution;
    private static class Node implements Comparable<Node> {
        WorldState worldState;
        // the number of moves made to reach this world state from the initial state.
        int moves;
        Node pre;
        int estDistance;

        Node(WorldState worldState, Node pre, int moves) {
            this.estDistance = worldState.estimatedDistanceToGoal();
            this.worldState = worldState;
            this.moves = moves;
            this.pre = pre;
        }

        @Override
        public int compareTo(Node o) {
            if ((o.getClass() == this.getClass())) {
                Node n = (Node) o;
                return (this.moves + this.estDistance) - (n.moves + n.estDistance);

            }
            return -1;
        }
    }
    public Solver(WorldState initial) {
        solution = new ArrayList<>();
        pq = new MinPQ<>();
        Node first = new Node(initial, null, 0);
        pq.insert(first);
//        while (!pq.isEmpty()) {
//            Node curr = pq.delMin();
//            solution.add(curr);
//            moves = curr.moves;
//            if (curr.worldState.isGoal()) {
//                return;
//            }
//            for (WorldState neighbor : curr.worldState.neighbors()) {
//                // the node will be added can not equal to it's parent
//                if (curr.pre == null || !neighbor.equals(curr.pre.worldState)) {
//                    Node n = new Node( neighbor, curr, curr.moves + 1);
//                    pq.insert(n);
//                }
//            }
//        }
        Node curr = pq.delMin();
        moves = curr.moves;
        while (!curr.worldState.isGoal()) {
            solution.add(curr);
            for (WorldState neighbor : curr.worldState.neighbors()) {
                // the node will be added can not equal to it's parent
                if (curr.pre == null || !neighbor.equals(curr.pre.worldState)) {
                    Node n = new Node(neighbor, curr, curr.moves + 1);
                    pq.insert(n);
                }
            }
            curr = pq.delMin();
            moves = curr.moves;
        }
        solution.add(curr);
    }



    public int moves() {
        return moves;
    }
    public Iterable<WorldState> solution() {
        List<WorldState> res = new ArrayList<>();
        for (Node node : solution) {
            res.add(node.worldState);
        }
        System.out.println("solution size = " + solution.size());
        return res;
    }
}
