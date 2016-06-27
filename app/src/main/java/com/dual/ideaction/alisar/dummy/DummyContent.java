package com.dual.ideaction.alisar.dummy;

import com.dual.ideaction.alisar.object.Terminal;
import com.dual.ideaction.alisar.object.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 1;

    static {
        /*
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
        */
        User user = User.getInstance();
        Iterator it = user.getTerminals().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String terminalId = (String) pair.getKey();
            Terminal terminal = (Terminal) pair.getValue();
            addItem(new DummyItem(terminalId, terminal.getTerminalName(), terminal));
        }

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final Terminal terminal;

        public DummyItem(String id, String content, Terminal terminal){
            this.id = String.valueOf(id);
            this.content = content;
            this.terminal = terminal;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
