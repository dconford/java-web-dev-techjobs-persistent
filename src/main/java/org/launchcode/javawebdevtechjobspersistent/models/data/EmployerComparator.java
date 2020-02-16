package org.launchcode.javawebdevtechjobspersistent.models.data;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;

import java.util.Comparator;

public class EmployerComparator implements Comparator<Employer> {
    @Override
    public int compare(Employer o1, Employer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
