package com.a1gym.manager.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Lcom/a1gym/manager/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "memberDao", "Lcom/a1gym/manager/data/dao/MemberDao;", "paymentDao", "Lcom/a1gym/manager/data/dao/PaymentDao;", "planDao", "Lcom/a1gym/manager/data/dao/PlanDao;", "Companion", "app_release"})
@androidx.room.Database(entities = {com.a1gym.manager.data.entity.Member.class, com.a1gym.manager.data.entity.Plan.class, com.a1gym.manager.data.entity.Payment.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.a1gym.manager.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.a1gym.manager.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.a1gym.manager.data.dao.MemberDao memberDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.a1gym.manager.data.dao.PlanDao planDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.a1gym.manager.data.dao.PaymentDao paymentDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/a1gym/manager/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/a1gym/manager/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "AppDatabaseCallback", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.a1gym.manager.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineScope scope) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/a1gym/manager/data/AppDatabase$Companion$AppDatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "populateDatabase", "planDao", "Lcom/a1gym/manager/data/dao/PlanDao;", "(Lcom/a1gym/manager/data/dao/PlanDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
        static final class AppDatabaseCallback extends androidx.room.RoomDatabase.Callback {
            @org.jetbrains.annotations.NotNull()
            private final kotlinx.coroutines.CoroutineScope scope = null;
            
            public AppDatabaseCallback(@org.jetbrains.annotations.NotNull()
            kotlinx.coroutines.CoroutineScope scope) {
                super();
            }
            
            @java.lang.Override()
            public void onCreate(@org.jetbrains.annotations.NotNull()
            androidx.sqlite.db.SupportSQLiteDatabase db) {
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Object populateDatabase(@org.jetbrains.annotations.NotNull()
            com.a1gym.manager.data.dao.PlanDao planDao, @org.jetbrains.annotations.NotNull()
            kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
                return null;
            }
        }
    }
}