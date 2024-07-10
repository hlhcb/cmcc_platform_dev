package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-04-23 12:59
 * @Description: App
 */
public class AppInspectionTaskInfoNew {
    /** 巡查人员*/
    private String name;

    /** 联防区域*/
    private String area1;

    /** 区域id*/
    private Long area1id;

    /** 巡查企业 */
    private String area2;

    /** 企业id */
    private Long area2id;

    /** 详细位置 */
    private String add;

    /** 是否存在治安问题*/
    private Integer problem1;

    /** 是否存在生产问题*/
    private Integer problem2;

    /** 是否存在消防问题*/
    private Integer problem3;

    /** 是否存在环境问题*/
    private Integer problem4;

    /** 是否存在矛盾问题*/
    private Integer problem5;

    /** 是否存在矛盾问题*/
    private Integer problem6;

    /** 巡查情况说明 */
    private String  introduction;

    /** 照片（最多2张）*/
    private List<String> urls;

    /** 是否存在治安问题*/
    private ProblemData problem1Data;

    /** 是否存在生产问题*/
    private ProblemData problem2Data;

    /** 是否存在消防问题*/
    private ProblemData problem3Data;

    /** 是否存在环境问题*/
    private ProblemData problem4Data;

    /** 是否存在矛盾问题*/
    private ProblemData problem5Data;

    /** 是否存在矛盾问题*/
    private ProblemData problem6Data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public Long getArea1id() {
        return area1id;
    }

    public void setArea1id(Long area1id) {
        this.area1id = area1id;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public Long getArea2id() {
        return area2id;
    }

    public void setArea2id(Long area2id) {
        this.area2id = area2id;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Integer getProblem1() {
        return problem1;
    }

    public void setProblem1(Integer problem1) {
        this.problem1 = problem1;
    }

    public Integer getProblem2() {
        return problem2;
    }

    public void setProblem2(Integer problem2) {
        this.problem2 = problem2;
    }

    public Integer getProblem3() {
        return problem3;
    }

    public void setProblem3(Integer problem3) {
        this.problem3 = problem3;
    }

    public Integer getProblem4() {
        return problem4;
    }

    public void setProblem4(Integer problem4) {
        this.problem4 = problem4;
    }

    public Integer getProblem5() {
        return problem5;
    }

    public void setProblem5(Integer problem5) {
        this.problem5 = problem5;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public ProblemData getProblem1Data() {
        return problem1Data;
    }

    public void setProblem1Data(ProblemData problem1Data) {
        this.problem1Data = problem1Data;
    }

    public ProblemData getProblem2Data() {
        return problem2Data;
    }

    public void setProblem2Data(ProblemData problem2Data) {
        this.problem2Data = problem2Data;
    }

    public ProblemData getProblem3Data() {
        return problem3Data;
    }

    public void setProblem3Data(ProblemData problem3Data) {
        this.problem3Data = problem3Data;
    }

    public ProblemData getProblem4Data() {
        return problem4Data;
    }

    public void setProblem4Data(ProblemData problem4Data) {
        this.problem4Data = problem4Data;
    }

    public ProblemData getProblem5Data() {
        return problem5Data;
    }

    public void setProblem5Data(ProblemData problem5Data) {
        this.problem5Data = problem5Data;
    }

    public Integer getProblem6() {
        return problem6;
    }

    public void setProblem6(Integer problem6) {
        this.problem6 = problem6;
    }

    public ProblemData getProblem6Data() {
        return problem6Data;
    }

    public void setProblem6Data(ProblemData problem6Data) {
        this.problem6Data = problem6Data;
    }
}
