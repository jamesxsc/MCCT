package com.georlegacy.general.mcct.data.storage;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.core.MCCTDataStore;

import java.io.*;

public final class DataFileManager {

    public DataFileManager() {
        this.file = new File(MCCTPlugin.getInstance().getDataFolder() + "store.mcct");
    }

    private MCCTDataStore dataStore;

    private File file;

    public void loadDataStore() {
        if (!file.exists()) {
            dataStore = new MCCTDataStore();
        } else {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                dataStore = (MCCTDataStore) inputStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void saveDataStore() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            file.delete();
            outputStream.writeObject(dataStore);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public MCCTDataStore getDataStore() {
        return dataStore;
    }

}
