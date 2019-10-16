    private boolean resolve(Resource resource, Resource[] locals, Resource[] remotes, boolean optional)
    {
        boolean result = true;

        // Check for cycles.
        if (m_resolveSet.contains(resource) || m_requiredSet.contains(resource) || m_optionalSet.contains(resource))
        {
            return true;
        }
        else if (m_failedSet.contains(resource))
        {
            return false;
        }

        // Add to resolve map to avoid cycles.
        m_resolveSet.add(resource);

        // Resolve the requirements for the resource according to the
        // search order of: added, resolving, local and finally remote
        // resources.
        Requirement[] reqs = resource.getRequirements();
        if (reqs != null)
        {
            Resource candidate;
            for (Requirement req : reqs) {
                // Do not resolve optional requirements
                if ((m_resolutionFlags & NO_OPTIONAL_RESOURCES) != 0 && req.isOptional()) {
                    continue;
                }
                candidate = searchResources(req, m_addedSet);
                if (candidate == null) {
                    candidate = searchResources(req, m_requiredSet);
                }
                if (candidate == null) {
                    candidate = searchResources(req, m_optionalSet);
                }
                if (candidate == null) {
                    candidate = searchResources(req, m_resolveSet);
                }
                if (candidate == null) {
                    List<ResourceCapability> candidateCapabilities = searchResources(req, locals);
                    candidateCapabilities.addAll(searchResources(req, remotes));

                    // Determine the best candidate available that
                    // can resolve.
                    while ((candidate == null) && !candidateCapabilities.isEmpty()) {
                        ResourceCapability bestCapability = getBestCandidate(candidateCapabilities);

                        // Try to resolve the best resource.
                        if (resolve(bestCapability.getResource(), locals, remotes, optional || req.isOptional())) {
                            candidate = bestCapability.getResource();
                        } else {
                            candidateCapabilities.remove(bestCapability);
                        }
                    }
                }

                if ((candidate == null) && !req.isOptional()) {
                    // The resolve failed.
                    result = false;
                    // Associated the current resource to the requirement
                    // in the unsatisfied requirement set.
                    m_unsatisfiedSet.add(new ReasonImpl(resource, req));
                } else if (candidate != null) {

                    // Try to resolve the candidate.
                    if (resolve(candidate, locals, remotes, optional || req.isOptional())) {
                        // The resolved succeeded; record the candidate
                        // as either optional or required.
                        if (optional || req.isOptional()) {
                            m_optionalSet.add(candidate);
                            m_resolveSet.remove(candidate);
                        } else {
                            m_requiredSet.add(candidate);
                            m_optionalSet.remove(candidate);
                            m_resolveSet.remove(candidate);
                        }

                        // Add the reason why the candidate was selected.
                        List<Reason> reasons = m_reasonMap.get(candidate);
                        if (reasons == null) {
                            reasons = new ArrayList<Reason>();
                            m_reasonMap.put(candidate, reasons);
                        }
                        reasons.add(new ReasonImpl(resource, req));
                    } else {
                        result = false;
                    }
                }
            }
        }

        // If the resolve failed, remove the resource from the resolve set and
        // add it to the failed set to avoid trying to resolve it again.
        if (!result)
        {
            m_resolveSet.remove(resource);
            m_failedSet.add(resource);
        }

        return result;
    }
