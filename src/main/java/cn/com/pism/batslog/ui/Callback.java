package cn.com.pism.batslog.ui;

/**
 * @author PerccyKing
 * @version 0.0.1
 * @date 2022/04/14 下午 11:24
 * @since 0.0.1
 */
@FunctionalInterface
public interface Callback<T> {
    /**
     * <p>
     * 回调方法
     * </p>
     *
     * @param t : 参数
     * @author PerccyKing
     * @date 2022/04/14 下午 11:25
     */
    void call(T t);
}
