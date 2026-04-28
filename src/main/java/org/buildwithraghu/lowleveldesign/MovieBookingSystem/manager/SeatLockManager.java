package org.buildwithraghu.lowleveldesign.MovieBookingSystem.manager;

import lombok.AllArgsConstructor;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.Screening;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.Seat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockManager {

    @AllArgsConstructor
    public class SeatLock {
        private final String userId;
        private final LocalDateTime expirationTime;

        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expirationTime);
        }
    }

    private final Map<String, SeatLock> lockedSeats = new ConcurrentHashMap<>();
    private final Duration lockDuration;

    public SeatLockManager(Duration lockDuration) {
        this.lockDuration = lockDuration;
    }

    public synchronized boolean lockSeat(Screening screening, Seat seat, String userId) {
        String lockKey = generateLockKey(screening, seat);
        cleanupLockIfExpired(lockKey);
        if (isLocked(screening, seat)) {
            return false;
        }
        SeatLock lock = new SeatLock(userId, LocalDateTime.now().plus(lockDuration));
        lockedSeats.put(lockKey, lock);
        return true;
    }

    private synchronized boolean isLocked(Screening screening, Seat seat) {
        String lockKey = generateLockKey(screening, seat);
        cleanupLockIfExpired(lockKey);
        return lockedSeats.containsKey(lockKey);
    }

    private void cleanupLockIfExpired(String lockKey) {
        SeatLock lock = lockedSeats.get(lockKey);
        if (lock != null && lock.isExpired()) {
            lockedSeats.remove(lockKey);
        }
    }

    private String generateLockKey(Screening screening, Seat seat) {
        return screening.getId() + "-" + seat.getSeatNumber();
    }

    public synchronized void unlockSeat(Screening screening, Seat seat) {
        String lockKey = generateLockKey(screening, seat);
        lockedSeats.remove(lockKey);
    }
}
