package trees.xmltree;

/*
 * created by raghavendra.ta on 28-Dec-2021
 */

class XMLTree {

    private XMLNode insert(String xml, int level) {

        XMLNode node = new XMLNode(level);
        StringBuilder contentBuilder = new StringBuilder();
        StringBuilder remainingContentBuilder = new StringBuilder();

        String tag = parseXMLTag(xml, contentBuilder, remainingContentBuilder);
        String content = contentBuilder.toString();

        node.setContent(content);

        if (tag == null) {
            return node;
        }

        node.setTag(tag);

        if (!remainingContentBuilder.isEmpty()) {
            node.children.add(insert(remainingContentBuilder.toString(), level));
        }

        XMLNode temp = insert(content, level + 1);
        if (temp.content != null && !temp.content.isEmpty())
            node.children.add(temp);

        return node;
    }

    private String parseXMLTag(String xml, StringBuilder contentBuilder, StringBuilder remainingContentBuilder) {

        int tagStartIdx = xml.indexOf('<');
        if (tagStartIdx == -1)
            return null;

        int tagEndIdx = xml.indexOf('>', tagStartIdx);
        if (tagEndIdx == -1)
            return null;

        String tag = xml.substring(tagStartIdx + 1, tagEndIdx);
        String endTag = "</" + tag + ">";
        int contentEndIdx = xml.indexOf("</" + tag + ">");
        if (contentEndIdx == -1)
            return null;

        contentBuilder.append(xml, tagEndIdx + 1, contentEndIdx);
        remainingContentBuilder.append(xml.substring(contentEndIdx + endTag.length()));
        return tag;
    }

    public XMLNode parseXML(String xml) {
        return this.insert(xml, 0);
    }

    public static void main(String[] args) {
        XMLNode node = new XMLTree().parseXML("<xml><div><h1>Raghu</h1><p>good student</p></div><div><h1>Name</h1></div></xml>");
        node.printTree("ROOT");
    }
}
