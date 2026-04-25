package trees.xmltree;

/*
 * created by raghavendra.ta on 28-Dec-2021
 */

import java.util.ArrayList;
import java.util.List;

class XMLNode {

    public String tag;

    public List<XMLNode> children;

    public String content;

    public int level;

    public XMLNode(int level) {

        this.children = new ArrayList<>();
        this.content = null;
        this.level = level;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasChild() {

        return content == null && !children.isEmpty();
    }

    public boolean hasContent() {

        return !hasChild();
    }

    public String getContent() {

        return content;
    }

    public void printTree(String parent) {
        String tab = " ".repeat(this.level * 4);
        System.out.println(tab + "Tag     : " + this.tag);
        System.out.println(tab + "Level   : " + this.level);
        System.out.println(tab + "Content : " + this.content);
        System.out.println(tab + "Parent  : " + parent);
        System.out.println("-".repeat(12 + this.content.length() + tab.length()));
        for(XMLNode node: this.children)
            node.printTree(this.tag);
    }


    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("<").append(tag).append(">");
        if (this.hasChild()) {
            for (XMLNode node : children) {
                builder.append(node.toString());
            }
        }
        else if (this.hasContent()) {
            builder.append(this.getContent());
        }
        builder.append("</").append(tag).append(">");
        return builder.toString();
    }
}
