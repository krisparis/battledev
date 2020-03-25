
/****************************************************************
/***/
/***/
/** SOLUTION BY Neumann*/
/***/
/***/
/********************************************************************/
package com.isograd.exercise;

import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class IsoContest {

    static class Node {
        List<Node> next = new ArrayList<>();
        String name;
        int firstHash;
        int secondHash;

        public Node(String name) {
            this.name = name;
        }
    }

    static class Bfs {
        Node node;
        int dist;

        public Bfs(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static void main(String[] argv) throws Exception {
                Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(new FileInputStream("H:\\Workspace\\untitled\\BattleDev\\kek\\input1.txt"));

        Map<String, Node> oldMap = new HashMap<>();
        for (int i = 0; i < 14; i++) {
            String name = Character.toString((char) (65 + i));
            oldMap.put(name, new Node(name));
        }
        oldMap.get("A").next.add(oldMap.get("F"));
        oldMap.get("A").next.add(oldMap.get("M"));
        oldMap.get("A").next.add(oldMap.get("J"));
        oldMap.get("B").next.add(oldMap.get("C"));
        oldMap.get("B").next.add(oldMap.get("K"));
        oldMap.get("B").next.add(oldMap.get("H"));
        oldMap.get("C").next.add(oldMap.get("B"));
        oldMap.get("C").next.add(oldMap.get("D"));
        oldMap.get("C").next.add(oldMap.get("G"));
        oldMap.get("D").next.add(oldMap.get("C"));
        oldMap.get("D").next.add(oldMap.get("G"));
        oldMap.get("D").next.add(oldMap.get("L"));
        oldMap.get("E").next.add(oldMap.get("G"));
        oldMap.get("E").next.add(oldMap.get("N"));
        oldMap.get("E").next.add(oldMap.get("M"));
        oldMap.get("F").next.add(oldMap.get("K"));
        oldMap.get("F").next.add(oldMap.get("A"));
        oldMap.get("F").next.add(oldMap.get("I"));
        oldMap.get("G").next.add(oldMap.get("D"));
        oldMap.get("G").next.add(oldMap.get("C"));
        oldMap.get("G").next.add(oldMap.get("E"));
        oldMap.get("H").next.add(oldMap.get("I"));
        oldMap.get("H").next.add(oldMap.get("B"));
        oldMap.get("H").next.add(oldMap.get("J"));
        oldMap.get("I").next.add(oldMap.get("F"));
        oldMap.get("I").next.add(oldMap.get("H"));
        oldMap.get("I").next.add(oldMap.get("L"));
        oldMap.get("J").next.add(oldMap.get("H"));
        oldMap.get("J").next.add(oldMap.get("A"));
        oldMap.get("J").next.add(oldMap.get("N"));
        oldMap.get("K").next.add(oldMap.get("B"));
        oldMap.get("K").next.add(oldMap.get("F"));
        oldMap.get("K").next.add(oldMap.get("L"));
        oldMap.get("L").next.add(oldMap.get("K"));
        oldMap.get("L").next.add(oldMap.get("I"));
        oldMap.get("L").next.add(oldMap.get("D"));
        oldMap.get("M").next.add(oldMap.get("E"));
        oldMap.get("M").next.add(oldMap.get("N"));
        oldMap.get("M").next.add(oldMap.get("A"));
        oldMap.get("N").next.add(oldMap.get("M"));
        oldMap.get("N").next.add(oldMap.get("E"));
        oldMap.get("N").next.add(oldMap.get("J"));

        Map<String, Node> newMap = new HashMap<>();

        String lostT = in.nextLine();
        for (int i = 0; i < 21; i++) {
            String[] temples = in.nextLine().split(" ");
            Node n1 = newMap.get(temples[1]);
            if (n1 == null) {
                n1 = new Node(temples[1]);
                newMap.put(n1.name, n1);
            }
            Node n0 = newMap.get(temples[0]);
            if (n0 == null) {
                n0 = new Node(temples[0]);
                newMap.put(n0.name, n0);
            }
            n0.next.add(n1);
            n1.next.add(n0);
        }

        computeHashes(oldMap);
        computeHashes(newMap);

        int targetH = oldMap.get(lostT).secondHash;

        for (Map.Entry<String, Node> e : newMap.entrySet()) {
            if (e.getValue().secondHash == targetH) {
                System.out.println(e.getKey());
                break;
            }
        }
    }

    static void computeHashes(Map<String, Node> newMap) {
        for (Map.Entry<String, Node> e : newMap.entrySet()) {
            int hash = getNodeHash(e.getValue());
            e.getValue().firstHash = hash;
        }

        for (Map.Entry<String, Node> e : newMap.entrySet()) {
            int hash = e.getValue().firstHash * 1000;
            for (Node nei : e.getValue().next) {
                hash += nei.firstHash;
            }
            e.getValue().secondHash = hash;
//            System.out.println("Node " + e.getKey() + " hash = " + hash);
        }
    }

    static int getNodeHash(Node n) {
        Set<String> done = new HashSet<>();

        int hash = 0;

        LinkedList<Bfs> queue = new LinkedList<>();
        queue.addLast(new Bfs(n, 0));
        done.add(n.name);
        while (!queue.isEmpty()) {
            Bfs c = queue.pollFirst();
            hash += c.dist * c.dist;
            for (Node nn : c.node.next) {
                if (!done.contains(nn.name)) {
                    queue.add(new Bfs(nn, c.dist + 1));
                    done.add(nn.name);
                }
            }
        }

        return hash;
    }
}

