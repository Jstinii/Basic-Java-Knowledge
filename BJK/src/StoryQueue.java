/*-------------------------------------------------------------------------
Author(s): Justin Ngo
Description: <Implementation of a Queue data structure>
<Queue interface for use in Story class>
<Methods include: add(),findNameInFront(),getFristName(),getName(),toStringInd()>
*/
public interface StoryQueue  {

    public void add(Object item);
    public boolean findNameInFront(Object item);
    public Object getFirstName();
    public Object getName(int j);
    public void setFirstName(Object item);
    public String toStringInd(int index);

}
