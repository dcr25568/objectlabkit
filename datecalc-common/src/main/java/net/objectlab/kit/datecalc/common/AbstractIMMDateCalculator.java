/*
 * $Id: AbstractDateCalculator.java 108 2006-09-05 10:13:01Z benoitx $
 * 
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
package net.objectlab.kit.datecalc.common;

import java.util.List;

/**
 * Abstract implementation in order to encapsulate all the common functionality
 * between Jdk and Joda implementations. It is parametrized on <code><E></code>
 * but basically <code>Date</code> and <code>LocalDate</code> are the only
 * viable values for it for now.
 * 
 * @author Marcin Jekot
 * @author $LastChangedBy: benoitx $
 * @version $Revision: 108 $ $Date: 2006-09-05 11:13:01 +0100 (Tue, 05 Sep 2006) $
 * 
 * @param <E>
 */
public abstract class AbstractIMMDateCalculator<E> implements IMMDateCalculator<E> {

    protected static final int MONTHS_IN_QUARTER = 3;

    protected static final int MONTH_IN_YEAR = 12;

    protected static final int DAYS_IN_WEEK = 7;

    /**
     * @param startDate
     * @return the next IMMDate based on current date.
     */
    public E getNextIMMDate(final E startDate) {
        return getNextIMMDate(true, startDate, IMMPeriod.QUARTERLY);
    }

    /**
     * @param startDate
     * @param period
     *            specify when the "next" IMM is, if quarterly then it is the
     *            conventional algorithm.
     * @return the next IMMDate based on current date.
     */
    public E getNextIMMDate(final E startDate, final IMMPeriod period) {
        return getNextIMMDate(true, startDate, period);
    }

    /**
     * @param startDate
     * @return the previous IMMDate based on current date.
     */
    public E getPreviousIMMDate(final E startDate) {
        return getNextIMMDate(false, startDate, IMMPeriod.QUARTERLY);
    }

    /**
     * @param startDate
     * @param period
     *            specify when the "previous" IMM is, if quarterly then it is
     *            the conventional algorithm.
     * @return the previous IMMDate based on current date.
     */
    public E getPreviousIMMDate(final E startDate, final IMMPeriod period) {
        return getNextIMMDate(false, startDate, period);
    }

    /**
     * Returns a list of IMM dates between 2 dates, it will exclude the start
     * date if it is an IMM date but would include the end date if it is an IMM
     * (same as IMMPeriod.QUARTERLY).
     * 
     * @param start
     *            start of the interval, excluded
     * @param end
     *            end of the interval, may be included.
     * @return list of IMM dates
     */
    public List<E> getIMMDates(final E start, final E end) {
        return getIMMDates(start, end, IMMPeriod.QUARTERLY);
    }

    protected abstract E getNextIMMDate(final boolean requestNextIMM, final E theStartDate, final IMMPeriod period);
}