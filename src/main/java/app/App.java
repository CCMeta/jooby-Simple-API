package app;

import java.util.*;
import java.sql.*;

import app.Controller.BaseController;
import io.jooby.Context;
import io.jooby.Jooby;
import io.jooby.MediaType;


import javax.annotation.Nonnull;

import app.Model.*;

public class App extends Jooby {

    private static ArrayList<String> _fuck;

    {
        //path configuration
        decorator(next -> ctx -> {
            var controller = ctx.getRequestPath().replace("/", "");
            var method = ctx.getMethod().toLowerCase();
            var body = BaseController.runRequest(controller,method);
//            ctx.setResponseCode(888);
            ctx.send(body);
            ctx.setDefaultResponseType(MediaType.json);
            return next.apply(ctx);
        });


        // Route configuration
        get("*", ctx -> "Welcome to Booby !");
//        get("/db", this::getDB);
//        get("/shit", ctx -> "fuck this shit bitch1");
//        get("/bad1", ctx -> {
//            badMotherfucker();
//            return App._fuck.toString();
//        });
    }

    public static void main(final String[] args) {
        runApp(args, App::new);
    }

    @Nonnull
    public Object getDB(Context ctx) throws SQLException {

        String sql = "SELECT json_agg(goods) FROM goods";
        var param = new HashMap<Integer, Object>();
        var response_text = Base.select(sql, param);
        ctx.setDefaultResponseType(MediaType.json);
        return response_text;
    }

    private void badMotherfucker() {
        var fuck = "fuck this shit bitch1";
        var list = new ArrayList<String>();
        list.add("郑大世");
        list.add("赵子惠");
//        list.forEach(System.out::println);
        var copy = List.copyOf(list);
        App._fuck = list;
//        System.out.println(copy);
//        System.out.println(fuck);
    }

}
