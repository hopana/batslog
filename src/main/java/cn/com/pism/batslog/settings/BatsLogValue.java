package cn.com.pism.batslog.settings;

import lombok.Data;

/**
 * @author PerccyKing
 * @version 0.0.1
 * @date 2020/11/01 下午 12:17
 * @since 0.0.1
 */
@Data
public class BatsLogValue<T> {
    private String type;
    private T value;
}
