package com.liugw.learn.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwoLock implements Lock {

	private class Sync extends AbstractQueuedSynchronizer {

		public Sync(int count) {
			setState(count);
		}

		@Override
		protected boolean isHeldExclusively() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected int tryAcquireShared(int arg) {
			for (;;) {
				int current = getState();
				int newState = current - arg;
				if (newState >= 0 || compareAndSetState(current, newState)) {
					return newState;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			for (;;) {
				int current = getState();
				int newState = current + arg;
				if (compareAndSetState(current, newState)) {
					return true;
				}
			}
		}

		Condition newCondition() {
			return new ConditionObject();
		}

	}

	private Sync sync = new Sync(2);

	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return sync.newCondition();
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return sync.tryAcquireShared(1) > 0;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return sync.tryAcquireSharedNanos(1, arg1.toNanos(arg0));
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		sync.releaseShared(1);
	}

}
