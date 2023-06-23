package com.example.nightskippervotemod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for handling resources, such as localized files.
 */
public class ResourceHandler {

    private static final Logger LOGGER = LogUtils.getLogger();
    private final String assetsPath = "/assets";

    private Localize localize;

    /**
     * Retrieves the localized resources for the EN_US language.
     *
     * @return The localized resources for EN_US.
     */
    public Localize getLocalizeENUSResource() {
        if (this.localize != null) {
            return this.localize;
        }

        this.localize = loadLocalizedResource("mod_lang_en_us.json");
        return this.localize;
    }

    /**
     * Retrieves the localized resources for the JA_JP language.
     *
     * @return The localized resources for JA_JP.
     */
    public Localize getLocalizeJAJPResource() {
        if (this.localize != null) {
            return this.localize;
        }

        this.localize = loadLocalizedResource("mod_lang_ja_jp.json");
        return this.localize;
    }

    /**
     * Loads the localized resource from the specified JSON file.
     *
     * @param fileName The name of the JSON file containing the localized resource.
     * @return The loaded localized resource.
     */
    private Localize loadLocalizedResource(String fileName) {
        InputStream inputStream = getClass().getResourceAsStream(assetsPath + "/" + fileName);

        if (inputStream != null) {
            try {
                return readStream(inputStream, new TypeToken<Localize>() {
                }.getType());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Reads and parses the JSON data from the input stream into the specified object type.
     *
     * @param inputStream The input stream containing the JSON data.
     * @param type        The type of object to parse the JSON into.
     * @param <T>         The type of the object.
     * @return The parsed object.
     * @throws IOException If an I/O error occurs during reading the stream.
     */
    private <T> T readStream(InputStream inputStream, Type type) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            json.append(line);
        }
        Gson gson = new Gson();
        T obj = gson.fromJson(json.toString(), type);
        LOGGER.debug(gson.toJson(obj));

        reader.close();
        bufferedReader.close();

        return obj;
    }
}

