package com.a1gym.manager.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.a1gym.manager.data.entity.Plan;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlanDao_Impl implements PlanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Plan> __insertionAdapterOfPlan;

  private final EntityInsertionAdapter<Plan> __insertionAdapterOfPlan_1;

  private final EntityDeletionOrUpdateAdapter<Plan> __deletionAdapterOfPlan;

  private final EntityDeletionOrUpdateAdapter<Plan> __updateAdapterOfPlan;

  public PlanDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlan = new EntityInsertionAdapter<Plan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plans` (`planId`,`planName`,`durationDays`,`price`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Plan entity) {
        statement.bindLong(1, entity.getPlanId());
        if (entity.getPlanName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPlanName());
        }
        statement.bindLong(3, entity.getDurationDays());
        statement.bindDouble(4, entity.getPrice());
      }
    };
    this.__insertionAdapterOfPlan_1 = new EntityInsertionAdapter<Plan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `plans` (`planId`,`planName`,`durationDays`,`price`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Plan entity) {
        statement.bindLong(1, entity.getPlanId());
        if (entity.getPlanName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPlanName());
        }
        statement.bindLong(3, entity.getDurationDays());
        statement.bindDouble(4, entity.getPrice());
      }
    };
    this.__deletionAdapterOfPlan = new EntityDeletionOrUpdateAdapter<Plan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `plans` WHERE `planId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Plan entity) {
        statement.bindLong(1, entity.getPlanId());
      }
    };
    this.__updateAdapterOfPlan = new EntityDeletionOrUpdateAdapter<Plan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `plans` SET `planId` = ?,`planName` = ?,`durationDays` = ?,`price` = ? WHERE `planId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Plan entity) {
        statement.bindLong(1, entity.getPlanId());
        if (entity.getPlanName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPlanName());
        }
        statement.bindLong(3, entity.getDurationDays());
        statement.bindDouble(4, entity.getPrice());
        statement.bindLong(5, entity.getPlanId());
      }
    };
  }

  @Override
  public Object insert(final Plan plan, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPlan.insertAndReturnId(plan);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<Plan> plans, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlan_1.insert(plans);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Plan plan, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPlan.handle(plan);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Plan plan, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPlan.handle(plan);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Plan>> getAllPlans() {
    final String _sql = "SELECT * FROM plans ORDER BY durationDays ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plans"}, new Callable<List<Plan>>() {
      @Override
      @NonNull
      public List<Plan> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "planId");
          final int _cursorIndexOfPlanName = CursorUtil.getColumnIndexOrThrow(_cursor, "planName");
          final int _cursorIndexOfDurationDays = CursorUtil.getColumnIndexOrThrow(_cursor, "durationDays");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final List<Plan> _result = new ArrayList<Plan>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Plan _item;
            final long _tmpPlanId;
            _tmpPlanId = _cursor.getLong(_cursorIndexOfPlanId);
            final String _tmpPlanName;
            if (_cursor.isNull(_cursorIndexOfPlanName)) {
              _tmpPlanName = null;
            } else {
              _tmpPlanName = _cursor.getString(_cursorIndexOfPlanName);
            }
            final int _tmpDurationDays;
            _tmpDurationDays = _cursor.getInt(_cursorIndexOfDurationDays);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            _item = new Plan(_tmpPlanId,_tmpPlanName,_tmpDurationDays,_tmpPrice);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
