    public void shouldNotifyDelegateMethodThatNodeIsNowNoLongerTheMasterNode() {
        DummyClusterWideSingletonService clusterWideSingletonService = spy(new DummyClusterWideSingletonService(ImmutableSettings.settingsBuilder().build()));
        clusterWideSingletonService.clusterChanged(localNodeBecameMasterEvent);
        clusterWideSingletonService.clusterChanged(localNodeNoLongerMasterEvent);

        verify(clusterWideSingletonService).serviceIsNoLongerOnMasterNode();
    }
