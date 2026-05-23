package io.github.machineswillrise.website.routing;

import io.github.machineswillrise.website.context.RequestContext;

@FunctionalInterface
public interface RouteAction {
	void execute(RequestContext ctx) throws Exception;
}
