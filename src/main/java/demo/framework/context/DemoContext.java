package demo.framework.context;

import java.util.List;

public class DemoContext {
    // Storing the Search List Items
    public static List<String> SerchReslt;
    public static int ListItemCount;


    public static List<String> getcnSerchReult() {
        return SerchReslt;
    }
    public static void setSerchReult(List<String> SerchReslt) {
        DemoContext.SerchReslt = SerchReslt;
    }

    public  static int getListItemCount(){return ListItemCount;}
    public  static void setListItemCount(int ListItemCount){DemoContext.ListItemCount=ListItemCount;}


}
