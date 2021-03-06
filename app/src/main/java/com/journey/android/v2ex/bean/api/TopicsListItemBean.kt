package com.journey.android.v2ex.bean.api

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TopicsListItemBean(
  @PrimaryKey
  var id: Int = 0,
  var title: String? = null,
  var url: String? = null,
  var content: String? = null,
  var replies: Int = 0,
  var memberName:String ="",
  var memberAvatar:String ="",
  var nodeName:String ="",
  var member: MemberBean? = MemberBean(),
  var node: NodeBean? = NodeBean(),
  var created: Int = 0,
  var last_modified: Int = 0,
  var last_modified_str: String? = null,
  var last_touched: Int = 0
) : RealmObject() {
  /**
   * id : 425341
   * title : Python 培训需要多少钱？老男孩学 Python
   * url : http://www.v2ex.com/t/425341
   * content : 文章来源：www.oldboyedu.com 10000+，开启逆袭模式，冲击年薪 40 万，做一名新时代不被淘汰的运维工程师！
   * replies : 0
   * member : {"id":276089,"username":"lnh2017","tagline":"","avatar_mini":"
   * node : {"id":90,"name":"python","title":"Python","title_alternative":"Python","url"
   * created : 1516704999
   * last_modified : 1516704999
   * last_touched : 1516690419
   */

}
