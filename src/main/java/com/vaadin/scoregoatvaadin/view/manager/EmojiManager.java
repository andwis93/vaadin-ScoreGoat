package com.vaadin.scoregoatvaadin.view.manager;

import com.vaadin.scoregoatvaadin.domain.Icon;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class EmojiManager {
    private final Map<Integer, String> emojiList;

    public EmojiManager() {
        this.emojiList = setEmojiList();
    }

    private Map<Integer, String> setEmojiList() {
        Map<Integer, String> list = new HashMap<>();
        list.put(0, Icon.ERROR.getIcon());
        list.put(1, Icon.CUP.getIcon());
        list.put(2, Icon.COOL.getIcon());
        list.put(3, Icon.CARTMAN.getIcon());
        list.put(4, Icon.HOMER.getIcon());
        list.put(5, Icon.CRYBABY.getIcon());
        return list;
    }
}
