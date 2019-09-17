package com.hf.game.sort;

import com.hf.game.module.items.Singleton;
import com.hf.game.module.items.TestItem;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 123 on 2019-8-28.
 */
public class MaoPaoSort {
    private MaoPaoSort() {

    }

    public static void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
     /*   int[] data = {3, 4, 2, 5, 5, 8, 8, 13, 0, 1};
        long start = System.currentTimeMillis();
        XuanZeSort.sort(data);
       long end= System.currentTimeMillis();
        System.out.println(end-start);
        for (int i = 0; i < data.length; i++){}
//            System.out.println(data[i]);*/
        /*TestItem instance = Singleton.INSTANCE.getInstance();
        TestItem testItem=Singleton.INSTANCE.getInstance();
        System.out.println(instance==testItem);*/


        TestItem obj = Singleton.INSTANCE.getInstance();
        TestItem obj1 = Singleton.INSTANCE.getInstance();
        obj.setBarcode("www");
        obj.setCategory_id1(555L);
        obj.setCategory_id2(666L);
        obj.setId(122L);
        obj.setItemName("busneeys");
        obj.setStatus((short) 1);
        FileOutputStream os = new FileOutputStream("obj.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(obj);
        oos.close();
        FileInputStream is = new FileInputStream("obj.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        TestItem o = (TestItem) ois.readObject();
        System.out.println(obj.hashCode());
        System.out.println(o.hashCode());
        System.out.println(obj);
        System.out.println(obj);
        System.out.println(o);
        System.out.println(Integer.toHexString(791885625));
        SerolizedObj obj2 = SerolizedObj.getObj();
//        System.out.println(obj2.hashCode());

        /*Class<SerolizedObj> aClass = SerolizedObj.class;
        Method method = aClass.getDeclaredMethod("readResolve");
        System.out.println(method);*/
       /* Class<FileOutputStream> aClass = FileOutputStream.class;
        Method open0 = aClass.getDeclaredMethod("open0", String.class, boolean.class);
        open0.setAccessible(true);
        open0.invoke(os, "obj1.txt", false);*/
    }
}
