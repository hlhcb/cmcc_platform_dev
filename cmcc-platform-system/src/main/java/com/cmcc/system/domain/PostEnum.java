package com.cmcc.system.domain;

public enum PostEnum {
    双主官(1,"双主官"),
    副所长(4,"副所长"),
    队长(5,"队长"),
    社区民警(6,"社区民警");

    private Integer postId;

    private String postName;

    private PostEnum(Integer postId, String postName)
    {
        this.postId = postId;
        this.postName = postName;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public static String getPostName(int psotId)
    {
        for (PostEnum item : PostEnum.values())
        {

            if (item.getPostId().intValue() == psotId)
            {
                return item.getPostName();
            }

        }
        return "";
    }
}
