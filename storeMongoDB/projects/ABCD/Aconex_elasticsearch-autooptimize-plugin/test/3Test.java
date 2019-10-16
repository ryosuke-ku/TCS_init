    public void shouldNotNotifyDelegateMethodsIfClusterStateEventHasNotReallyChanged() {
        DummyClusterWideSingletonService clusterWideSingletonService = spy(new DummyClusterWideSingletonService(ImmutableSettings.settingsBuilder().build()));
        clusterWideSingletonService.clusterChanged(localNodeBecameMasterEvent);
        clusterWideSingletonService.clusterChanged(localNodeBecameMasterEvent);

        verify(clusterWideSingletonService, times(1)).serviceIsNowOnMasterNode();
    }
