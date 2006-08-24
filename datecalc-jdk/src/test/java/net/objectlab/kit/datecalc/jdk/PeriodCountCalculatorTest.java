/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.objectlab.kit.datecalc.jdk;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Assert;
import net.objectlab.kit.datecalc.common.PeriodCountBasis;

public class PeriodCountCalculatorTest extends AbstractDateCalculatorTest {

    private static final String[][] ACT_365 = {
            // name start end period dayDiff yearDiff
            { "0D", "2006-08-08", "2006-08-08", "ACT_365", "0", "0.00" },
            { "1D", "2006-08-08", "2006-08-09", "ACT_365", "1", "0.002740" },
            { "1W", "2006-08-08", "2006-08-15", "ACT_365", "7", "0.019178" },
            { "1M", "2006-08-08", "2006-09-08", "ACT_365", "31", "0.084932" },
            { "3M", "2006-08-08", "2006-11-08", "ACT_365", "92", "0.252055" },
            { "6M", "2006-08-08", "2007-02-08", "ACT_365", "184", "0.504110" },
            { "9M", "2006-08-08", "2007-05-08", "ACT_365", "273", "0.747945" },
            { "1Y", "2006-08-08", "2007-08-08", "ACT_365", "365", "1.000000" },
            { "2Y", "2006-08-08", "2008-08-08", "ACT_365", "731", "2.002740" },
            { "3Y", "2006-08-08", "2009-08-10", "ACT_365", "1098", "3.008219" },
            { "4Y", "2006-08-08", "2010-08-09", "ACT_365", "1462", "4.005479" },
            { "5Y", "2006-08-08", "2011-08-08", "ACT_365", "1826", "5.002740" },
            { "7Y", "2006-08-08", "2013-08-08", "ACT_365", "2557", "7.005479" },
            { "10Y", "2006-08-08", "2016-08-08", "ACT_365", "3653", "10.008219" },
            { "15Y", "2006-08-08", "2021-08-09", "ACT_365", "5480", "15.013699" },
            { "20Y", "2006-08-08", "2026-08-10", "ACT_365", "7307", "20.019178" },
            { "30Y", "2006-08-08", "2036-08-08", "ACT_365", "10958", "30.021918" },
            // leap year - funny date...
            { "0D", "2008-02-29", "2008-02-29", "ACT_365", "0", "0.00" },
            { "1D", "2008-02-29", "2008-03-03", "ACT_365", "3", "0.008219" },
            { "1W", "2008-02-29", "2008-03-07", "ACT_365", "7", "0.019178" },
            { "1M", "2008-02-29", "2008-03-31", "ACT_365", "31", "0.084932" },
            { "3M", "2008-02-29", "2008-05-29", "ACT_365", "90", "0.246575" },
            { "6M", "2008-02-29", "2008-08-29", "ACT_365", "182", "0.498630" },
            { "9M", "2008-02-29", "2008-11-28", "ACT_365", "273", "0.747945" },
            { "1Y", "2008-02-29", "2009-02-27", "ACT_365", "364", "0.997260" },
            { "2Y", "2008-02-29", "2010-02-26", "ACT_365", "728", "1.994521" },
            { "3Y", "2008-02-29", "2011-02-28", "ACT_365", "1095", "3.000000" },
            { "4Y", "2008-02-29", "2012-02-29", "ACT_365", "1461", "4.002740" },
            { "5Y", "2008-02-29", "2013-02-28", "ACT_365", "1826", "5.002740" },
            { "7Y", "2008-02-29", "2015-02-27", "ACT_365", "2555", "7.000000" },
            { "10Y", "2008-02-29", "2018-02-28", "ACT_365", "3652", "10.005479" },
            { "15Y", "2008-02-29", "2023-02-28", "ACT_365", "5478", "15.008219" },
            { "20Y", "2008-02-29", "2028-02-29", "ACT_365", "7305", "20.013699" },
            { "30Y", "2008-02-29", "2038-02-26", "ACT_365", "10955", "30.013699" }
    // end of set
    };

    private static final String[][] ACT_360 = {
            // name start end period dayDiff yearDiff
            { "0D", "2006-08-08", "2006-08-08", "ACT_360", "0", "0.00" },
            { "1D", "2006-08-08", "2006-08-09", "ACT_360", "1", "0.002778" },
            { "1W", "2006-08-08", "2006-08-15", "ACT_360", "7", "0.019444" },
            { "1M", "2006-08-08", "2006-09-08", "ACT_360", "31", "0.086111" },
            { "3M", "2006-08-08", "2006-11-08", "ACT_360", "92", "0.255556" },
            { "6M", "2006-08-08", "2007-02-08", "ACT_360", "184", "0.511111" },
            { "9M", "2006-08-08", "2007-05-08", "ACT_360", "273", "0.758333" },
            { "1Y", "2006-08-08", "2007-08-08", "ACT_360", "365", "1.013889" },
            { "2Y", "2006-08-08", "2008-08-08", "ACT_360", "731", "2.030556" },
            { "3Y", "2006-08-08", "2009-08-10", "ACT_360", "1098", "3.050000" },
            { "4Y", "2006-08-08", "2010-08-09", "ACT_360", "1462", "4.061111" },
            { "5Y", "2006-08-08", "2011-08-08", "ACT_360", "1826", "5.072222" },
            { "7Y", "2006-08-08", "2013-08-08", "ACT_360", "2557", "7.102778" },
            { "10Y", "2006-08-08", "2016-08-08", "ACT_360", "3653", "10.147222" },
            { "15Y", "2006-08-08", "2021-08-09", "ACT_360", "5480", "15.222222" },
            { "20Y", "2006-08-08", "2026-08-10", "ACT_360", "7307", "20.297222" },
            { "30Y", "2006-08-08", "2036-08-08", "ACT_360", "10958", "30.438889" },
            // leap year - funny date...
            { "0D", "2008-02-29", "2008-02-29", "ACT_360", "0", "0.00" },
            { "1D", "2008-02-29", "2008-03-03", "ACT_360", "3", "0.008333" },
            { "1W", "2008-02-29", "2008-03-07", "ACT_360", "7", "0.019444" },
            { "1M", "2008-02-29", "2008-03-31", "ACT_360", "31", "0.086111" },
            { "3M", "2008-02-29", "2008-05-29", "ACT_360", "90", "0.250000" },
            { "6M", "2008-02-29", "2008-08-29", "ACT_360", "182", "0.505556" },
            { "9M", "2008-02-29", "2008-11-28", "ACT_360", "273", "0.758333" },
            { "1Y", "2008-02-29", "2009-02-27", "ACT_360", "364", "1.011111" },
            { "2Y", "2008-02-29", "2010-02-26", "ACT_360", "728", "2.022222" },
            { "3Y", "2008-02-29", "2011-02-28", "ACT_360", "1095", "3.041667" },
            { "4Y", "2008-02-29", "2012-02-29", "ACT_360", "1461", "4.058333" },
            { "5Y", "2008-02-29", "2013-02-28", "ACT_360", "1826", "5.072222" },
            { "7Y", "2008-02-29", "2015-02-27", "ACT_360", "2555", "7.097222" },
            { "10Y", "2008-02-29", "2018-02-28", "ACT_360", "3652", "10.144444" },
            { "15Y", "2008-02-29", "2023-02-28", "ACT_360", "5478", "15.216667" },
            { "20Y", "2008-02-29", "2028-02-29", "ACT_360", "7305", "20.291667" },
            { "30Y", "2008-02-29", "2038-02-26", "ACT_360", "10955", "30.430556" }
    // end of set
    };

    private static final String[][] CONV_30_360 = {
            // name start end period dayDiff yearDiff
            { "0D", "2006-08-08", "2006-08-08", "CONV_30_360", "0", "0.00" },
            { "1D", "2006-08-08", "2006-08-09", "CONV_30_360", "1", "0.002778" },
            { "1W", "2006-08-08", "2006-08-15", "CONV_30_360", "7", "0.019444" },
            { "1M", "2006-08-08", "2006-09-08", "CONV_30_360", "30", "0.083333" },
            { "3M", "2006-08-08", "2006-11-08", "CONV_30_360", "90", "0.250000" },
            { "6M", "2006-08-08", "2007-02-08", "CONV_30_360", "180", "0.500000" },
            { "9M", "2006-08-08", "2007-05-08", "CONV_30_360", "270", "0.750000" },
            { "1Y", "2006-08-08", "2007-08-08", "CONV_30_360", "360", "1.000000" },
            { "2Y", "2006-08-08", "2008-08-08", "CONV_30_360", "720", "2.000000" },
            { "3Y", "2006-08-08", "2009-08-10", "CONV_30_360", "1082", "3.005556" },
            { "4Y", "2006-08-08", "2010-08-09", "CONV_30_360", "1441", "4.002778" },
            { "5Y", "2006-08-08", "2011-08-08", "CONV_30_360", "1800", "5.000000" },
            { "7Y", "2006-08-08", "2013-08-08", "CONV_30_360", "2520", "7.000000" },
            { "10Y", "2006-08-08", "2016-08-08", "CONV_30_360", "3600", "10.000000" },
            { "15Y", "2006-08-08", "2021-08-09", "CONV_30_360", "5401", "15.002778" },
            { "20Y", "2006-08-08", "2026-08-10", "CONV_30_360", "7202", "20.005556" },
            { "30Y", "2006-08-08", "2036-08-08", "CONV_30_360", "10800", "30.000000" },
            // leap year - funny date...
            { "0D", "2008-02-29", "2008-02-29", "CONV_30_360", "0", "0.00" },
            { "1D", "2008-02-29", "2008-03-03", "CONV_30_360", "4", "0.011111" },
            { "1W", "2008-02-29", "2008-03-07", "CONV_30_360", "8", "0.022222" },
            { "1M", "2008-02-29", "2008-03-31", "CONV_30_360", "32", "0.088889" },
            { "3M", "2008-02-29", "2008-05-29", "CONV_30_360", "90", "0.250000" },
            { "6M", "2008-02-29", "2008-08-29", "CONV_30_360", "180", "0.500000" },
            { "9M", "2008-02-29", "2008-11-28", "CONV_30_360", "269", "0.747222" },
            { "1Y", "2008-02-29", "2009-02-27", "CONV_30_360", "358", "0.994444" },
            { "2Y", "2008-02-29", "2010-02-26", "CONV_30_360", "717", "1.991667" },
            { "3Y", "2008-02-29", "2011-02-28", "CONV_30_360", "1079", "2.997222" },
            { "4Y", "2008-02-29", "2012-02-29", "CONV_30_360", "1440", "4.000000" },
            { "5Y", "2008-02-29", "2013-02-28", "CONV_30_360", "1799", "4.997222" },
            { "7Y", "2008-02-29", "2015-02-27", "CONV_30_360", "2518", "6.994444" },
            { "10Y", "2008-02-29", "2018-02-28", "CONV_30_360", "3599", "9.997222" },
            { "15Y", "2008-02-29", "2023-02-28", "CONV_30_360", "5399", "14.997222" },
            { "20Y", "2008-02-29", "2028-02-29", "CONV_30_360", "7200", "20.000000" },
            { "30Y", "2008-02-29", "2038-02-26", "CONV_30_360", "10797", "29.991667" }
    // end of set
    };

    private static final String[][] CONV_360E_ISDA = {
            // name start end period dayDiff yearDiff
            { "0D", "2006-08-08", "2006-08-08", "CONV_360E_ISDA", "0", "0.00" },
            { "1D", "2006-08-08", "2006-08-09", "CONV_360E_ISDA", "1", "0.002778" },
            { "1W", "2006-08-08", "2006-08-15", "CONV_360E_ISDA", "7", "0.019444" },
            { "1M", "2006-08-08", "2006-09-08", "CONV_360E_ISDA", "30", "0.083333" },
            { "3M", "2006-08-08", "2006-11-08", "CONV_360E_ISDA", "90", "0.250000" },
            { "6M", "2006-08-08", "2007-02-08", "CONV_360E_ISDA", "180", "0.500000" },
            { "9M", "2006-08-08", "2007-05-08", "CONV_360E_ISDA", "270", "0.750000" },
            { "1Y", "2006-08-08", "2007-08-08", "CONV_360E_ISDA", "360", "1.000000" },
            { "2Y", "2006-08-08", "2008-08-08", "CONV_360E_ISDA", "720", "2.000000" },
            { "3Y", "2006-08-08", "2009-08-10", "CONV_360E_ISDA", "1082", "3.005556" },
            { "4Y", "2006-08-08", "2010-08-09", "CONV_360E_ISDA", "1441", "4.002778" },
            { "5Y", "2006-08-08", "2011-08-08", "CONV_360E_ISDA", "1800", "5.000000" },
            { "7Y", "2006-08-08", "2013-08-08", "CONV_360E_ISDA", "2520", "7.000000" },
            { "10Y", "2006-08-08", "2016-08-08", "CONV_360E_ISDA", "3600", "10.000000" },
            { "15Y", "2006-08-08", "2021-08-09", "CONV_360E_ISDA", "5401", "15.002778" },
            { "20Y", "2006-08-08", "2026-08-10", "CONV_360E_ISDA", "7202", "20.005556" },
            { "30Y", "2006-08-08", "2036-08-08", "CONV_360E_ISDA", "10800", "30.000000" },
            // leap year - funny date...
            { "0D", "2008-02-29", "2008-02-29", "CONV_360E_ISDA", "0", "0.00" },
            { "1D", "2008-02-29", "2008-03-03", "CONV_360E_ISDA", "4", "0.011111" },
            { "1W", "2008-02-29", "2008-03-07", "CONV_360E_ISDA", "8", "0.022222" },
            { "1M", "2008-02-29", "2008-03-31", "CONV_360E_ISDA", "31", "0.086111" },
            { "3M", "2008-02-29", "2008-05-29", "CONV_360E_ISDA", "90", "0.250000" },
            { "6M", "2008-02-29", "2008-08-29", "CONV_360E_ISDA", "180", "0.500000" },
            { "9M", "2008-02-29", "2008-11-28", "CONV_360E_ISDA", "269", "0.747222" },
            { "1Y", "2008-02-29", "2009-02-27", "CONV_360E_ISDA", "358", "0.994444" },
            { "2Y", "2008-02-29", "2010-02-26", "CONV_360E_ISDA", "717", "1.991667" },
            { "3Y", "2008-02-29", "2011-02-28", "CONV_360E_ISDA", "1079", "2.997222" },
            { "4Y", "2008-02-29", "2012-02-29", "CONV_360E_ISDA", "1440", "4.000000" },
            { "5Y", "2008-02-29", "2013-02-28", "CONV_360E_ISDA", "1799", "4.997222" },
            { "7Y", "2008-02-29", "2015-02-27", "CONV_360E_ISDA", "2518", "6.994444" },
            { "10Y", "2008-02-29", "2018-02-28", "CONV_360E_ISDA", "3599", "9.997222" },
            { "15Y", "2008-02-29", "2023-02-28", "CONV_360E_ISDA", "5399", "14.997222" },
            { "20Y", "2008-02-29", "2028-02-29", "CONV_360E_ISDA", "7200", "20.000000" },
            { "30Y", "2008-02-29", "2038-02-26", "CONV_360E_ISDA", "10797", "29.991667" }
    // end of set
    };

    private static final String[][] CONV_360E_ISMA = {
            // name start end period dayDiff yearDiff
            { "0D", "2006-08-08", "2006-08-08", "CONV_360E_ISMA", "0", "0.00" },
            { "1D", "2006-08-08", "2006-08-09", "CONV_360E_ISMA", "1", "0.002778" },
            { "1W", "2006-08-08", "2006-08-15", "CONV_360E_ISMA", "7", "0.019444" },
            { "1M", "2006-08-08", "2006-09-08", "CONV_360E_ISMA", "30", "0.083333" },
            { "3M", "2006-08-08", "2006-11-08", "CONV_360E_ISMA", "90", "0.250000" },
            { "6M", "2006-08-08", "2007-02-08", "CONV_360E_ISMA", "180", "0.500000" },
            { "9M", "2006-08-08", "2007-05-08", "CONV_360E_ISMA", "270", "0.750000" },
            { "1Y", "2006-08-08", "2007-08-08", "CONV_360E_ISMA", "360", "1.000000" },
            { "2Y", "2006-08-08", "2008-08-08", "CONV_360E_ISMA", "720", "2.000000" },
            { "3Y", "2006-08-08", "2009-08-10", "CONV_360E_ISMA", "1082", "3.005556" },
            { "4Y", "2006-08-08", "2010-08-09", "CONV_360E_ISMA", "1441", "4.002778" },
            { "5Y", "2006-08-08", "2011-08-08", "CONV_360E_ISMA", "1800", "5.000000" },
            { "7Y", "2006-08-08", "2013-08-08", "CONV_360E_ISMA", "2520", "7.000000" },
            { "10Y", "2006-08-08", "2016-08-08", "CONV_360E_ISMA", "3600", "10.000000" },
            { "15Y", "2006-08-08", "2021-08-09", "CONV_360E_ISMA", "5401", "15.002778" },
            { "20Y", "2006-08-08", "2026-08-10", "CONV_360E_ISMA", "7202", "20.005556" },
            { "30Y", "2006-08-08", "2036-08-08", "CONV_360E_ISMA", "10800", "30.000000" },
            // leap year - funny date...
            { "0D", "2008-02-29", "2008-02-29", "CONV_360E_ISMA", "0", "0.00" },
            { "1D", "2008-02-29", "2008-03-03", "CONV_360E_ISMA", "4", "0.011111" },
            { "1W", "2008-02-29", "2008-03-07", "CONV_360E_ISMA", "8", "0.022222" },
            { "1M", "2008-02-29", "2008-03-31", "CONV_360E_ISMA", "31", "0.086111" },
            { "3M", "2008-02-29", "2008-05-29", "CONV_360E_ISMA", "90", "0.250000" },
            { "6M", "2008-02-29", "2008-08-29", "CONV_360E_ISMA", "180", "0.500000" },
            { "9M", "2008-02-29", "2008-11-28", "CONV_360E_ISMA", "269", "0.747222" },
            { "1Y", "2008-02-29", "2009-02-27", "CONV_360E_ISMA", "358", "0.994444" },
            { "2Y", "2008-02-29", "2010-02-26", "CONV_360E_ISMA", "717", "1.991667" },
            { "3Y", "2008-02-29", "2011-02-28", "CONV_360E_ISMA", "1079", "2.997222" },
            { "4Y", "2008-02-29", "2012-02-29", "CONV_360E_ISMA", "1440", "4.000000" },
            { "5Y", "2008-02-29", "2013-02-28", "CONV_360E_ISMA", "1799", "4.997222" },
            { "7Y", "2008-02-29", "2015-02-27", "CONV_360E_ISMA", "2518", "6.994444" },
            { "10Y", "2008-02-29", "2018-02-28", "CONV_360E_ISMA", "3599", "9.997222" },
            { "15Y", "2008-02-29", "2023-02-28", "CONV_360E_ISMA", "5399", "14.997222" },
            { "20Y", "2008-02-29", "2028-02-29", "CONV_360E_ISMA", "7200", "20.000000" },
            { "30Y", "2008-02-29", "2038-02-26", "CONV_360E_ISMA", "10797", "29.991667" }
    // end of set
    };

    public void testConv30EvIsma() {
        final PeriodCountCalculator cal = DefaultDateCalculatorFactory.getDefaultInstance().getPeriodCountCalculator();
        Assert.assertNotNull(cal);

        for (final String[] test : CONV_360E_ISMA) {
            runtest(cal, test);
        }
    }

    public void testConv30Ev360() {
        final PeriodCountCalculator cal = DefaultDateCalculatorFactory.getDefaultInstance().getPeriodCountCalculator();
        Assert.assertNotNull(cal);

        for (final String[] test : CONV_360E_ISDA) {
            runtest(cal, test);
        }
    }

    public void testConv30v360() {
        final PeriodCountCalculator cal = DefaultDateCalculatorFactory.getDefaultInstance().getPeriodCountCalculator();
        Assert.assertNotNull(cal);

        for (final String[] test : CONV_30_360) {
            runtest(cal, test);
        }
    }

    public void testAct365() {
        final PeriodCountCalculator cal = DefaultDateCalculatorFactory.getDefaultInstance().getPeriodCountCalculator();
        Assert.assertNotNull(cal);

        for (final String[] test : ACT_365) {
            runtest(cal, test);
        }
    }

    public void testAct360() {
        final PeriodCountCalculator cal = DefaultDateCalculatorFactory.getDefaultInstance().getPeriodCountCalculator();
        Assert.assertNotNull(cal);

        for (final String[] test : ACT_360) {
            runtest(cal, test);
        }
    }

    private void runtest(final PeriodCountCalculator cal, final String[] test) {
        final String name = test[0];
        final Date start = createDate(test[1]);
        final Date end = createDate(test[2]);
        final PeriodCountBasis pcount = PeriodCountBasis.valueOf(test[3]);
        final int dayDiff = Integer.parseInt(test[4]);
        Assert.assertEquals(name + " Basis:" + pcount + " start:" + start + " dayDiff", dayDiff, cal.dayDiff(start, end, pcount));

        final BigDecimal yearDiff = new BigDecimal(cal.yearDiff(start, end, pcount)).setScale(6, BigDecimal.ROUND_HALF_UP);
        Assert.assertTrue(name + " Basis:" + pcount + " yearDiff expected:" + test[5] + " got:" + yearDiff, (new BigDecimal(
                test[5])).compareTo(yearDiff) == 0);
    }

}