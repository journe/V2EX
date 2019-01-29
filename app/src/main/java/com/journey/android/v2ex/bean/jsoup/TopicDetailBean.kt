package com.journey.android.v2ex.bean.jsoup

import java.util.regex.Pattern

class TopicDetailBean {
    private val PATTERN = Pattern.compile("/t/(\\d+?)(?:\\W|$)")
    private val PATTERN_TOPIC_REPLY_TIME = "at (.+?),".toRegex()
    private val PATTERN_POSTSCRIPT = "·\\s+(.+)".toRegex()
    private val PATTERN_NUMBERS = "\\d+".toRegex()

    var id: Int = 0
    var title: String = ""
    var content: String = ""
    var node: String = ""
    var nodeUrl: String = ""
    var replies: Int = 0
    var replyTime: String = ""
    var memberBean: MemberBean = MemberBean("", "")
    var topicComments: ArrayList<TopicCommentBean>? = null
}