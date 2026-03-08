package com.a1gym.manager.ui.members

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class MemberProfileFragmentArgs(
  public val memberId: Long = -1L,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("memberId", this.memberId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("memberId", this.memberId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): MemberProfileFragmentArgs {
      bundle.setClassLoader(MemberProfileFragmentArgs::class.java.classLoader)
      val __memberId : Long
      if (bundle.containsKey("memberId")) {
        __memberId = bundle.getLong("memberId")
      } else {
        __memberId = -1L
      }
      return MemberProfileFragmentArgs(__memberId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): MemberProfileFragmentArgs {
      val __memberId : Long?
      if (savedStateHandle.contains("memberId")) {
        __memberId = savedStateHandle["memberId"]
        if (__memberId == null) {
          throw IllegalArgumentException("Argument \"memberId\" of type long does not support null values")
        }
      } else {
        __memberId = -1L
      }
      return MemberProfileFragmentArgs(__memberId)
    }
  }
}
