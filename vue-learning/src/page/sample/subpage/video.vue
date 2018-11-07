<template>
    <VideoPlayer v-if="loading" :options="playerOptions">
    </VideoPlayer>
</template>
<script>
import VideoPlayer from '@/components/VideoPlayer';

export default {
    components: {
        VideoPlayer,
    },
    data() {
        return {
            loading: false,
            playerOptions: {
                muted: false,
                playbackRates: [0.7, 1.0, 1.25, 1.5, 2.0],
                sources: [
                    {
                        type: 'video/mp4',
                        src: 'http://vjs.zencdn.net/v/oceans.mp4',
                        // src: '/file/data/2018-09-06/222238099533463552.mp4',
                    },
                ],
                height: 350,
            },
        };
    },
    created() {
        setTimeout(() => {
            this.loading = true;
        }, 2000);
    },
};
</script>
