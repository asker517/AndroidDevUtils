/**
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tencent.nbagametime.utils;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Subscriber;

/**
 * Throttle by windowing a stream and returning the first value in each window.
 *
 * @param <T> the value type
 */
public final class AndroidThrottleFirst<T> implements Operator<T, T> {

  final long timeInMilliseconds;

  public AndroidThrottleFirst(long windowDuration, TimeUnit unit) {
    this.timeInMilliseconds = unit.toMillis(windowDuration);
  }

  @Override
  public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
    return new Subscriber<T>(subscriber) {

      private long lastOnNext = -1;

      @Override
      public void onStart() {
        request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(T v) {
        long now = SystemClock.uptimeMillis();
        if (lastOnNext == -1 || now - lastOnNext >= timeInMilliseconds) {
          lastOnNext = now;
          subscriber.onNext(v);
        }
      }

      @Override
      public void onCompleted() {
        subscriber.onCompleted();
      }

      @Override
      public void onError(Throwable e) {
        subscriber.onError(e);
      }
    };
  }
}
