package siren;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author jonfreer
 * @since 8/20/17
 */
public class LinkTester {

    private Link.Builder linkBuilder;

    public LinkTester(){}

    @Before
    public void setup(){
        this.linkBuilder = new Link.Builder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rel_nullRelAsString_outcomeIs_IllegalArgumentException() throws URISyntaxException {

        //arrange.
        final String rel = null;

        //action.
        this.linkBuilder.rel(rel).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rel_nullRelAsURI_outcomeIs_IllegalArgumentException() throws URISyntaxException {

        //arrange.
        final URI rel = null;

        //action.
        this.linkBuilder.rel(rel).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rel_nullRelAsRelation_outcomeIs_IllegalArgumentException() throws URISyntaxException {

        //arrange.
        final Relation rel = null;

        //action.
        this.linkBuilder.rel(rel).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void klass_nullKlass_outcomeIs_IllegalArgumentException() {

        //arrange.
        final String klass = null;

        //action.
        this.linkBuilder.klass(klass).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void href_nullHref_outcomeIs_IllegalArgumentException() {

        //arrange.
        final URI href = null;

        //action.
        this.linkBuilder.href(href).build();
    }

    @Test
    public void getRel_outcomeIs_RelRetrieved() throws URISyntaxException {

        //arrange.
        final String uri = "http://www.example.com/href";
        final String rel1 = "relTest";
        final String rel2 = "relTest2";
        final Relation relation1 = new Relation(rel1);
        final Relation relation2 = new Relation(rel2);
        final URI href = new URI(uri);

        Link link =
            this.linkBuilder
                .rels(relation1, relation2)
                .href(href)
                .build();

        //action.
        List<Relation> actualRel = link.getRel();

        //assert.
        Assert.assertEquals(2, actualRel.size());
        Assert.assertEquals(relation1, actualRel.get(0));
        Assert.assertEquals(relation2, actualRel.get(1));
    }

    @Test
    public void getKlass_outcomeIs_ClassRetrieved() throws URISyntaxException {

        //arrange.
        final String klass1 = "klassTest";
        final String klass2 = "klassTest2";
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);
        final String rel1 = "testRel1";
        final String rel2 = "testRel2";

        Link link =
            this.linkBuilder
                .rels(rel1, rel2)
                .href(href)
                .klasses(klass1, klass2)
                .build();

        //action.
        List<String> actualClass = link.getKlass();

        //assert.
        Assert.assertEquals(2, actualClass.size());
        Assert.assertEquals(klass1, actualClass.get(0));
        Assert.assertEquals(klass2, actualClass.get(1));
    }

    @Test
    public void getKlass_nullClass_outcomeIs_Null() throws URISyntaxException {

        //arrange.
        final String klass = "klassTest";
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);
        final URI rel1 = new URI("http://www.example.com/relation1");
        final URI rel2 = new URI("http://www.example.com/relation2");

        Link link =
            this.linkBuilder
                .rels(rel1, rel2)
                .href(href)
                .build();

        //action.
        List<String> actualClass = link.getKlass();

        //assert.
        Assert.assertNull(actualClass);
    }

    @Test
    public void getTitle_outcomeIs_TitleRetrieved() throws URISyntaxException {

        //arrange.
        final String title = "titleTest";
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);

        Link link =
            this.linkBuilder
                .rel("relTest")
                .href(href)
                .title(title)
                .build();

        //action.
        String actualTitle = link.getTitle();

        //assert.
        Assert.assertEquals(title, actualTitle);
    }

    @Test
    public void getTitle_nullTitle_outcomeIs_Null() throws URISyntaxException {

        //arrange.
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);
        Link link =
            this.linkBuilder
                .rel("relTest")
                .href(href)
                .build();

        //action.
        String actualTitle = link.getTitle();

        //assert.
        Assert.assertNull(actualTitle);
    }

    @Test
    public void getType_outcomeIs_TypeRetrieved() throws URISyntaxException {

        //arrange.
        final String type = "typeTest";
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);
        Link link =
            this.linkBuilder
                .rel("relTest")
                .href(href)
                .type(type)
                .build();

        //action.
        String actualType = link.getType();

        //assert.
        Assert.assertEquals(type, actualType);
    }

    @Test
    public void getType_nullTitle_outcomeIs_Null() throws URISyntaxException {

        //arrange.
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);
        Link link =
            this.linkBuilder
                .rel("relTest")
                .href(href)
                .build();

        //action.
        String actualType = link.getType();

        //assert.
        Assert.assertNull(actualType);
    }

    @Test
    public void getHref_outcomeIs_HrefRetrieved() throws URISyntaxException {

        //arrange.
        final String uri = "http://www.example.com/href";
        final URI href = new URI(uri);
        Link link =
            this.linkBuilder
                .rel("relTest")
                .href(href)
                .build();

        //action.
        URI actualHref = link.getHref();

        //assert.
        Assert.assertEquals(href, actualHref);
    }

    @Test
    public void equals_instancesAreEqualWithNulls_outcomeIs_true() throws URISyntaxException {

        //arrange.
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");

        Link link1 = this.linkBuilder.rel(rel).href(href).build();

        this.linkBuilder.clear();

        Link link2 = this.linkBuilder.rel(rel).href(href).build();

        //action.
        boolean areEqual = link1.equals(link2);

        //assert.
        Assert.assertTrue(areEqual);
    }

    @Test
    public void equals_instancesAreEqual_outcomeIs_true() throws URISyntaxException {

        //arrange.
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");
        final String title = "testTitle";
        final String type = "testType";
        final String klass = "testKlass";

        Link link1 =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(klass)
                .build();

        this.linkBuilder.clear();

        Link link2 =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(klass)
                .build();

        //action.
        boolean areEqual = link1.equals(link2);

        //assert.
        Assert.assertTrue(areEqual);
    }

    @Test
    public void equals_instanceIsNull_outcomeIs_false() throws URISyntaxException {

        //arrange.
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");
        final String title = "testTitle";
        final String type = "testType";
        final String klass = "testKlass";

        Link link1 =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(klass)
                .build();

        this.linkBuilder.clear();

        Link link2 = null;

        //action.
        boolean areEqual = link1.equals(link2);

        //assert.
        Assert.assertFalse(areEqual);
    }

    @Test
    public void equals_mismatchingClass_outcomeIs_false() throws URISyntaxException {

        //arrange.
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");
        final String title = "testTitle";
        final String type = "testType";
        final String klass = "testKlass";

        Link link1 =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(klass)
                .build();

        this.linkBuilder.clear();

        Object link2 = new Object();

        //action.
        boolean areEqual = link1.equals(link2);

        //assert.
        Assert.assertFalse(areEqual);
    }

    @Test
    public void equals_instancesAreNotEqual_outcomeIs_false() throws URISyntaxException {

        //arrange.
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");
        final String title = "testTitle";
        final String type = "testType";
        final String klass = "testKlass";
        final String otherKlass = "otherTestKlass";

        Link link1 =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(klass)
                .build();

        this.linkBuilder.clear();

        Link link2 =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(otherKlass) //different class.
                .build();

        //action.
        boolean areEqual = link1.equals(link2);

        //assert.
        Assert.assertFalse(areEqual);
    }

    @Test
    public void hashCode_outcomeIs_hashCodeGenerated() throws URISyntaxException {

        //arrange.
        final int PRIME = 31;
        int expectedHashCode = 1;
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");
        final String title = "testTitle";
        final String type = "testType";
        final String klass = "testKlass";

        Link link =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .title(title)
                .type(type)
                .klass(klass)
                .build();

        expectedHashCode *= PRIME + link.getTitle().hashCode();
        expectedHashCode *= PRIME + link.getKlass().hashCode();
        expectedHashCode *= PRIME + link.getType().hashCode();
        expectedHashCode *= PRIME + link.getHref().hashCode();
        expectedHashCode *= PRIME + link.getRel().hashCode();

        //action.
        int actualHashCode = link.hashCode();

        //assert.
        Assert.assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void hashCode_membersAreNull_outcomeIs_hashCodeGenerated() throws URISyntaxException {

        //arrange.
        final int PRIME = 31;
        int expectedHashCode = 1;
        final String rel = "testRel";
        final URI href = new URI("http://www.example.com/something");

        Link link =
            this.linkBuilder
                .rel(rel)
                .href(href)
                .build();

        expectedHashCode *= PRIME + link.getHref().hashCode();
        expectedHashCode *= PRIME + link.getRel().hashCode();

        //action.
        int actualHashCode = link.hashCode();

        //assert.
        Assert.assertEquals(expectedHashCode, actualHashCode);
    }

    @After
    public void tearDown(){
        this.linkBuilder = null;
    }
}
