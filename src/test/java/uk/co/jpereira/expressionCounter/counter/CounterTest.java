package uk.co.jpereira.expressionCounter.counter;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joao Pereira on 23/07/2015.
 */
public class CounterTest extends TestCase {
    private final String ipsilum = "Lorem ipsum ad his scripta blandit partiendo, eum fastidii accumsan euripidis in, eum liber hendrerit an. Qui ut wisi vocibus suscipiantur, quo dicit ridens inciderint id. Quo mundi lobortis reformidans eu, legimus senserit definiebas an eos. Eu sit tincidunt incorrupte definitionem, vis mutat affert percipit cu, eirmod consectetuer signiferumque eu per. In usu latine equidem dolores. Quo no falli viris intellegam, ut fugit veritus placerat per.\n";
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
        Counter cnt = new Counter();
        Hashtable<String, Integer> retVal = new Hashtable<String, Integer>();
        Assert.assertEquals(retVal, cnt.getResult());
        retVal.put("Bamm", 1);
        cnt.setCaseSensitive(true);
        cnt.calculate("Bamm");
        Assert.assertEquals(retVal, cnt.getResult());
    }

    @Test
    public void testCalculateSimpleExampleCaseSensitive() throws Exception {
        Counter cnt = new Counter();
        String text = "Lorem ipsum ad his scripta blandit partiendo. Scripta ad His.";
        Hashtable<String, Integer> retVal;
        retVal = cnt.calculate(text);
        cnt.setCaseSensitive(true);
        Assert.assertEquals(2, retVal.get("ad").intValue());
        Assert.assertEquals(1, retVal.get("his").intValue());
        Assert.assertEquals(1, retVal.get("Scripta").intValue());
    }
    @Test
    public void testCalculateSimpleExampleCaseInsensitive() throws Exception {
        Counter cnt = new Counter();
        String text = "Lorem ipsum ad his scripta blandit partiendo. Scripta ad His.";
        Hashtable<String, Integer> retVal;
        cnt.setCaseSensitive(false);
        retVal = cnt.calculate(text);

        Assert.assertEquals(2, retVal.get("ad").intValue());
        Assert.assertEquals(2, retVal.get("his").intValue());
        Assert.assertNull(retVal.get("Scripta"));
        Assert.assertEquals(2, retVal.get("scripta").intValue());
        Assert.assertEquals(1, retVal.get("partiendo").intValue());
    }


    @Test
    public void testCalculateWithIgnoreListExampleCaseSensitive() throws Exception {
        ArrayList<String> ignoreList = new ArrayList<String>();
        ignoreList.add("His");
        ignoreList.add("$\\wd^");
        ignoreList.add("$L.+sum");
        Counter cnt = new Counter(ignoreList);
        String text = "Lorem ipsum ad his scripta blandit partiendo. Scripta ad His.";
        Hashtable<String, Integer> retVal;
        retVal = cnt.calculate(text);
        cnt.setCaseSensitive(true);
        Assert.assertNull(retVal.get("ad"));
        Assert.assertNull(retVal.get("Lorem"));
        Assert.assertEquals(1, retVal.get("his").intValue());
        Assert.assertEquals(1, retVal.get("Scripta").intValue());
    }
    @Test
    public void testCalculateWithIgnoreListExampleCaseInsensitive() throws Exception {
        ArrayList<String> ignoreList = new ArrayList<String>();
        ignoreList.add("His");
        ignoreList.add("$\\wd^");
        ignoreList.add("$L.+sum");
        Counter cnt = new Counter();
        String text = "Lorem ipsum ad his scripta blandit partiendo. Scripta ad His.";
        Hashtable<String, Integer> retVal;
        cnt.setCaseSensitive(false);
        retVal = cnt.calculate(text);

        Assert.assertEquals(2, retVal.get("his").intValue());
        Assert.assertNull(retVal.get("ad"));
        Assert.assertNull(retVal.get("lorem"));
        Assert.assertNull(retVal.get("Scripta"));
        Assert.assertEquals(2, retVal.get("scripta").intValue());
        Assert.assertEquals(1, retVal.get("partiendo").intValue());
    }
}