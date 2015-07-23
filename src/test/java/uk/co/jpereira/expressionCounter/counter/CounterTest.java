package uk.co.jpereira.expressionCounter.counter;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joao Pereira on 23/07/2015.
 */
public class CounterTest extends TestCase {

    @Test
    public void testSetMergeList() throws Exception {
        Counter cnt = new Counter();
        Hashtable<String, List<String>> mergeList = new Hashtable<String, List<String>>();
        mergeList.put("bamm", new ArrayList<String>());
        mergeList.put("bamm1", new ArrayList<String>());

        mergeList.get("bamm").add("asd");
        mergeList.get("bamm").add("asd2");
        mergeList.get("bamm").add("asd3");
        mergeList.get("bamm1").add("asd1");

        cnt.setMergeList(mergeList);
        Assert.assertEquals(mergeList, cnt.getMergeList());
        mergeList.put("new line", new ArrayList<String>());
        cnt.setMergeList(mergeList);

        Hashtable retVal = cnt.getMergeList();
        Assert.assertNotEquals(mergeList, retVal);

        Assert.assertTrue(null != retVal.get("new@@@@@line"));
    }

    @Test
    public void testGetResult() throws Exception {

    }

    @Test
    public void testCalculate() throws Exception {

    }
}