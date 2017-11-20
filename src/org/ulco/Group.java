package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    public Group() {
        m_allObjectList = new Vector<GraphicsObject>();
        m_ID = ++ID.ID;
    }

    public Group(String json) {
        m_allObjectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public void add(Object object) {
        if (object instanceof Group) {
            addGroup((Group)object);
        } else {
            addObject((GraphicsObject)object);
        }
    }
    public boolean isSimple(){return false;}

    @Override
    boolean isClosed(Point pt, double distance) {
        return false;
    }

    private void addGroup(Group group) {
        m_allObjectList.add(group);
    }

    private void addObject(GraphicsObject object) {
        m_allObjectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (GraphicsObject o : m_allObjectList) {
            g.add(o);
        }
        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        for (GraphicsObject o : m_allObjectList) {
            o.move(delta);
        }
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_allObjectList.add(JSON.parseGroup(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_allObjectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size=0;
        for (GraphicsObject o : m_allObjectList) {
            if (o.isSimple()) {
                size++;
            } else {
                size += ((Group) o).size();
            }
        }

        return size;
    }


        public String toJson() {
        String str = "{ type: group, objects : { ";

       for (int i = 0; i < m_allObjectList.size(); ++i) {
           if (m_allObjectList.elementAt(i).isSimple()){
               GraphicsObject element = m_allObjectList.elementAt(i);

               str += element.toJson();
               if (i < m_allObjectList.size() - 1) {
                   str += ", ";
               }
           }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_allObjectList.size(); ++i) {
            if (!m_allObjectList.elementAt(i).isSimple()) {
                GraphicsObject element = m_allObjectList.elementAt(i);

                str += element.toJson();
            }
        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";
        int cpt=0;

       for (int i = 0; i < m_allObjectList.size(); ++i) {
           if (m_allObjectList.elementAt(i).isSimple()){
               GraphicsObject element = m_allObjectList.elementAt(i);
               if (cpt>0) {
                   str += ", ";
               }
               str += element.toString();
               cpt++;
           }
        }
        str += "],[";

        for (int i = 0; i < m_allObjectList.size(); ++i) {
            if (!m_allObjectList.get(i).isSimple()) {
                GraphicsObject element = m_allObjectList.elementAt(i);

                str += element.toString();
            }
        }
        return str + "]]";
    }

    private Vector<GraphicsObject> m_allObjectList;
    private int m_ID;
}
