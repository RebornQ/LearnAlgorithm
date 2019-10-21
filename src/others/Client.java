package others;

import others.pattern_string.Anagram;

/**
 * Created by reborn on 2019-10-20 22:22
 */
public class Client {

    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        anagram.isAnagram("acb", "abc");
        anagram.isAnagramV2("acb", "abc");
    }
}
