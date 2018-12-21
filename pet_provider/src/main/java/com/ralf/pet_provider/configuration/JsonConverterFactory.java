package com.ralf.pet_provider.configuration;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.ralf.pet_provider.user.UserUtil;
import com.ralf.pet_provider.user.constant.UserConstant;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name JsonConverterFactory
 * @email -
 * @date 2018/12/21 下午2:35
 **/
public class JsonConverterFactory extends Converter.Factory {

    public static JsonConverterFactory create() {
        return create(new Gson());
    }

    public static JsonConverterFactory create(Gson gson) {
        return new JsonConverterFactory(gson);

    }

    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(gson, adapter); //请求
    }

    public static class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private static final Charset UTF_8 = Charset.forName("UTF-8");
        private static final String TAG = "Json请求体加密";
        private final Gson gson;
        private final TypeAdapter<T> adapter;

        JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(@NonNull T value) throws IOException {
            JsonObject object = (JsonObject) value;
            if (UserConstant.APP_USERID == -1) {
                UserConstant.APP_USERID = UserUtil.getUserId();

            }
            object.addProperty(UserConstant.USER_ID, UserConstant.APP_USERID);
            object.addProperty(UserConstant.USER_TOKEN, UserConstant.APP_TOKEN);
            Log.d(TAG, "convert: 参数" + object.toString());
            return RequestBody.create(MEDIA_TYPE, gson.toJson(object));
        }
    }

}
