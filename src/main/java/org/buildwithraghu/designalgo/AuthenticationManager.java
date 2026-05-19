package org.buildwithraghu.designalgo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthenticationManager {

    private final int timeToLive;
    private final Map<String, Integer> tokenMap;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.tokenMap = new LinkedHashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        tokenMap.remove(tokenId); // This is required because data structure is of type LinkedList
        tokenMap.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        Integer exp = tokenMap.get(tokenId);
        if (exp != null && exp > currentTime) {
            tokenMap.remove(tokenId);
            tokenMap.put(tokenId, currentTime + timeToLive);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        Iterator<Map.Entry<String, Integer>> it = tokenMap.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() <= currentTime) {
                it.remove();
            } else {
                break;
            }
        }
        return tokenMap.size();
    }
}
