package config.lincat.journal;

/**
 * 日志消息的种类
 */
enum JournalTipType {
    /**
     * 普通消息
     */
    MESSAGE,
    /**
     * 强调消息
     */
    STRESS,
    /**
     * 异常
     */
    EXCEPTION,
    /**
     * 错误
     */
    WRONG
}
