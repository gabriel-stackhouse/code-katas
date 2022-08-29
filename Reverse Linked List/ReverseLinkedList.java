/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        int curPosition = 1;
        ListNode curNode = head;
        List<ListNode> leftNodes = new ArrayList<>();
        Stack<ListNode> middleNodes = new Stack<>();
        List<ListNode> rightNodes = new ArrayList<>();
        while (curPosition < left && curNode != null) {
            leftNodes.add(curNode);
            curPosition++;
            curNode = curNode.next;
        }
        while (curPosition >= left && curPosition <= right && curNode != null) {
            middleNodes.push(curNode);
            curPosition++;
            curNode = curNode.next;
        }
        while (curNode != null) {
            rightNodes.add(curNode);
            curNode = curNode.next;
        }
        List<ListNode> allNodes = combine(leftNodes, middleNodes, rightNodes);
        for (int i = 0; i < allNodes.size() - 1; i++) {
            allNodes.get(i).next = allNodes.get(i + 1);
        }
        allNodes.get(allNodes.size() - 1).next = null;
        return allNodes.get(0);
    }
    
    private List<ListNode> combine(List<ListNode> leftNodes, Stack<ListNode> middleNodes, List<ListNode> rightNodes) {
        List<ListNode> result = new ArrayList<>();
        result.addAll(leftNodes);
        while (!middleNodes.empty()) {
            result.add(middleNodes.pop());
        }
        result.addAll(rightNodes);
        return result;
    }
}