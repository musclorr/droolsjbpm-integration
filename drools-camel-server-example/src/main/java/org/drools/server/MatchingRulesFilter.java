package org.drools.server;

import org.kie.api.runtime.ClassObjectFilter;

public class MatchingRulesFilter extends ClassObjectFilter {
	public MatchingRulesFilter() {
		super(MatchingRule.class);
	}
}
