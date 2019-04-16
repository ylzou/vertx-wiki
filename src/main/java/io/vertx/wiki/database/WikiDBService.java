package io.vertx.wiki.database;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

import java.util.HashMap;

@ProxyGen
@VertxGen
public interface WikiDBService {
  @Fluent
  WikiDBService fetchAllPages(Handler<AsyncResult<JsonArray>> resultHandler);

  @Fluent
  WikiDBService fetchPage(String name, Handler<AsyncResult<JsonObject>> resultHandler);

  @Fluent
  WikiDBService createPage(String title, String markdown, Handler<AsyncResult<Void>> resultHandler);

  @Fluent
  WikiDBService savePage(int id, String markdown, Handler<AsyncResult<Void>> resultHandler);

  @Fluent
  WikiDBService deletePage(int id, Handler<AsyncResult<Void>> resultHandler);

  @GenIgnore
  static WikiDBService create(JDBCClient dbClient, HashMap<SqlQuery, String> sqlQueries, Handler<AsyncResult<WikiDBService>> readyHandler) {
    return new WikiDBServiceImpl(dbClient, sqlQueries, readyHandler);
  }
  @GenIgnore
  static WikiDBService createProxy(Vertx vertx, String address) {
    return new WikiDBServiceVertxEBProxy(vertx, address);
  }
}
