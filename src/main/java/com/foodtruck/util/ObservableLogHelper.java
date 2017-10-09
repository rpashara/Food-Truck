/**
 * 
 */
package com.foodtruck.util;

import org.slf4j.Logger;
import rx.Notification;
import rx.functions.Action1;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author rpashara
 *
 */
public class ObservableLogHelper {
	
	public static <T>Action1<Notification<? super T>> log(Logger logger, String message, String offset) {
        AtomicReference<String> nextOffset = new AtomicReference<String>(">>");
        return (Notification<? super T> notification) -> {
            switch (notification.getKind()) {
                case OnNext:
                    logger.debug("Executed OnNext on thread -> {} | {} : {}{}{}",Thread.currentThread().getName(),message,offset,nextOffset.get(),notification.getValue());
                    break;
                case OnError:
                    logger.debug("Executed OnError on thread -> {} | {} : {}{}{}",Thread.currentThread().getName(),message,offset,nextOffset.get(),notification.getThrowable().getMessage(), notification.getThrowable());
                    break;
                case OnCompleted:
                    logger.debug("Executed OnCompleted on thread -> {} | {} : {}{}**",Thread.currentThread().getName(),message,offset,nextOffset.get());
                    break;
                default:
                    break;
            };
        };
    }

    public static <T>Action1<Notification<? super T>> log(Logger logger, String message) {
        return log(logger,message,"");
    }
}
