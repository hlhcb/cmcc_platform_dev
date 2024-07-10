<template>
  <div class="app-container home">
    <!-- 南移后台管理系统 -->
    <!-- <iframe src="http://36.137.58.190:51818/link/KBrLybfG" frameborder="0"></iframe> -->
    <!-- <IFRAME v-if="showOperate"
    src="https://wx.liushaotou.ltd:20008/#/delink?link=Wt4WI1UrG1f9ZRp6yaL7crbzufcrfrOeL%2FTgxKxV1BfNeqWvi5AgOX9KgyLs6wa05%2BhrcWA8BDcDl5Z1bJ4DUw&user=C93vMgwJGFexODRj6998yd%2FgkL1mFbarW9yabu%2FN%2F5iPJ6KyWUnsEqAJguntxnpRiyoxC7dUihyZ2H45jWlSmA"
    frameBorder=0 marginwidth=0 marginheight=0 scrolling=no
    style="width:100%;height:720px"
    ALLOWTRANSPARENCY="true"></IFRAME> -->
  </div>
</template>

<script>
import { appcheckadmin} from "@/api/officer/inteviewinfo";
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.7",
      showOperate: false
    };
  },
  created() {
    this.checkadmin()
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    checkadmin()
    {
      appcheckadmin().then(response => {
        this.showOperate = response.data
      });
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

