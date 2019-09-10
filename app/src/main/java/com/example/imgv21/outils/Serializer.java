package com.example.imgv21.outils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;

public abstract class Serializer {


    /**
     * Serialisation of the object
     * @param filename
     * @param object
     * @param context
     */
    public static void Serialize(String filename, Object object, Context context){
        try{
            FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos;
            try{
                oos = new ObjectOutputStream(file);
                oos.writeObject(object);
                oos.flush();
                oos.close();
            }catch(IOException e){
                //error of serialisation
                e.printStackTrace();
            }
        }catch(FileNotFoundException e){
            //file not found
            e.printStackTrace();
        }
    }

    /**
     * Deserialise Object
     * @param filename
     * @param context
     * @return
     */
    public static Object deserialize(String filename, Context context){
        try{
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream ois;
            try{
                ois = new ObjectInputStream(file);
                try{
                    Object object = ois.readObject();
                    ois.close();
                    return  object;
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }catch (StreamCorruptedException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
