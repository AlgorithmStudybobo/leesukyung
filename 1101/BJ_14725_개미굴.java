package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_14725_개미굴 {
	static class TrieNode {
		Map<String, TrieNode> node = new TreeMap<>();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		TrieNode root = new TrieNode();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			TrieNode cur = root;
			for (int j = 0; j < m; j++) {
				String str = st.nextToken();
				if(cur.node.get(str)==null) {
					cur.node.put(str, new TrieNode());
				}
				cur= cur.node.get(str);
			}
		}
		
		print(root, "");
		
	}
	
	static void print(TrieNode trie, String str) {
		if(trie.node!=null) {
			for(Entry<String, TrieNode> e: trie.node.entrySet()) {
				System.out.println(str+e.getKey());
				print(trie.node.get(e.getKey()), str+"--");
			}
		}
		
	}
	
}
