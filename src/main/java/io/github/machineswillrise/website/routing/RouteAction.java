package io.github.machineswillrise.website.routing;

@FunctionalInterface
public interface RouteAction {
	void execute(RequestContext ctx) throws Exception;
}
