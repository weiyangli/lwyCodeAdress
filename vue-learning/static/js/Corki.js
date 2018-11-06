if (!LOLherojs) var LOLherojs = {champion: {}};
LOLherojs.champion.Corki = {
    "data": {
        "id": "Corki",
        "key": "42",
        "name": "\u82f1\u52c7\u6295\u5f39\u624b",
        "title": "\u5e93\u5947",
        "tags": ["Marksman"],
        "image": {
            "full": "Corki.png",
            "sprite": "champion0.png",
            "group": "champion",
            "x": 384,
            "y": 48,
            "w": 48,
            "h": 48
        },
        "skins": [{"id": "42000", "num": 0, "name": "default", "chromas": false}, {
            "id": "42001",
            "num": 1,
            "name": "\u5916\u661f\u88c5\u7532 \u5e93\u5947",
            "chromas": false
        }, {"id": "42002", "num": 2, "name": "\u96ea\u6a47\u901f\u6ed1 \u5e93\u5947", "chromas": false}, {
            "id": "42003",
            "num": 3,
            "name": "\u7ea2\u8272\u7537\u7235 \u5e93\u5947",
            "chromas": false
        }, {"id": "42004", "num": 4, "name": "\u6025\u901f\u524d\u8fdb \u5e93\u5947", "chromas": false}, {
            "id": "42005",
            "num": 5,
            "name": "\u6d77\u725b\u9a91\u624b  \u5e93\u5947",
            "chromas": false
        }, {"id": "42006", "num": 6, "name": "\u795e\u9f99\u4e4b\u7ffc \u5e93\u5947", "chromas": true}, {
            "id": "42007",
            "num": 7,
            "name": "Fnatic \u5e93\u5947",
            "chromas": false
        }, {"id": "42008", "num": 8, "name": "\u7535\u73a9\u4e0a\u6821 \u5e93\u5947", "chromas": true}],
        "info": {"attack": 8, "defense": 3, "magic": 6, "difficulty": 6},
        "spells": [{
            "id": "PhosphorusBomb",
            "name": "\u78f7\u5149\u70b8\u5f39",
            "description": "\u5e93\u5947\u5411\u76ee\u6807\u533a\u57df\u53d1\u5c04\u4e00\u679a\u95ea\u5149\u5f39\uff0c\u5bf9\u8303\u56f4\u5185\u7684\u654c\u4eba\u9020\u6210\u9b54\u6cd5\u4f24\u5bb3\u3002\u8fd9\u4e2a\u6280\u80fd\u8fd8\u4f1a\u6682\u65f6\u8ba9\u76ee\u6807\u533a\u57df\u9644\u8fd1\u7684\u975e\u9690\u5f62\u7684\u5355\u4f4d\u548c\u82f1\u96c4\u73b0\u5f62\u3002",
            "image": {
                "full": "PhosphorusBomb.png",
                "sprite": "spell2.png",
                "group": "spell",
                "x": 144,
                "y": 48,
                "w": 48,
                "h": 48
            },
            "tooltip": "\u5e93\u5947\u8f70\u51fa\u4e00\u679a\u70b8\u5f39\uff0c\u5bf9\u76ee\u6807\u8303\u56f4\u5185\u654c\u4eba\u9020\u621075\/120\/165\/210\/255 <scaleAD>(+0.5)<\/scaleAD> <scaleAP>(+0.5)<\/scaleAP>\u9b54\u6cd5\u4f24\u5bb3\uff0c\u5e76\u663e\u793a\u8be5\u533a\u57df\uff0c\u6301\u7eed6\u79d2\uff08\u4e0d\u663e\u5f62\u9690\u8eab\u5355\u4f4d\uff09\u3002",
            "leveltip": {
                "label": ["\u4f24\u5bb3", "@AbilityResourceName@\u6d88\u8017"],
                "effect": ["75\/120\/165\/210\/255", "60\/70\/80\/90\/100"]
            },
            "resource": "60\/70\/80\/90\/100 "
        }, {
            "id": "CarpetBomb",
            "name": "\u74e6\u5c14\u57fa\u91cc\u4fef\u51b2",
            "description": "\u5e93\u5947\u98de\u884c\u4e00\u5c0f\u6bb5\u8ddd\u79bb\uff0c\u540c\u65f6\u6295\u4e0b\u70b8\u5f39\u3002\u70b8\u5f39\u4f1a\u5236\u9020\u51fa\u4e00\u6761\u6bc1\u706d\u4e0e\u6b7b\u4ea1\u7684\u8def\u5f84\uff0c\u5bf9\u7559\u5728\u8def\u5f84\u4e2d\u7684\u654c\u4eba\u9020\u6210\u4f24\u5bb3\u3002",
            "image": {
                "full": "CarpetBomb.png",
                "sprite": "spell2.png",
                "group": "spell",
                "x": 192,
                "y": 48,
                "w": 48,
                "h": 48
            },
            "tooltip": "<spellPassive>\u4e3b\u52a8\uff1a<\/spellPassive>\u5e93\u5947\u98de\u884c\u4e00\u5c0f\u6bb5\u8ddd\u79bb\uff0c\u7559\u4e0b\u4e00\u6761\u6bc1\u706d\u4e0e\u6b7b\u4ea1\u4e4b\u8def\uff0c\u5bf9\u7559\u5728\u8def\u5f84\u4e2d\u7684\u654c\u4eba\u9020\u6210\u6bcf\u79d260\/90\/120\/150\/180<scaleAP>(+0.4)<\/scaleAP>\u9b54\u6cd5\u4f24\u5bb3\u3002<br \/><br \/><spellPassive>\u7279\u522b\u5feb\u9012\uff1a<\/spellPassive>\u5e93\u5947\u98de\u884c\u4e00\u5927\u6bb5\u8ddd\u79bb\uff0c\u6295\u4e0b\u4e00\u9897\u80fd\u9707\u5f00\u654c\u4eba\u7684\u70b8\u5f39\u5e76\u7559\u4e0b\u4e00\u6761\u6bc1\u706d\u4e0e\u6b7b\u4ea1\u7684\u8def\u5f84\uff0c\u6301\u7eed5\u79d2\u3002\u8def\u5f84\u4e2d\u7684\u654c\u4eba\u4f1a\u88ab\u51cf\u901f90%\u5e76\u88ab\u707c\u70e7\uff0c\u6bcf\u79d2\u53d7\u5230<scaleAD>(+)<\/scaleAD> <scaleAP>(+0.2)<\/scaleAP>\u9b54\u6cd5\u4f24\u5bb3\u3002",
            "leveltip": {
                "label": ["\u4f24\u5bb3", "\u51b7\u5374\u65f6\u95f4"],
                "effect": ["60\/90\/120\/150\/180", "20\/19\/18\/17\/16"]
            },
            "resource": "100 "
        }, {
            "id": "GGun",
            "name": "\u683c\u6797\u673a\u67aa",
            "description": "\u5e93\u5947\u4f7f\u7528\u683c\u6797\u673a\u67aa\u626b\u5c04\u4ed6\u9762\u524d\u9525\u5f62\u8303\u56f4\u5185\u7684\u654c\u4eba\uff0c\u9020\u6210\u4f24\u5bb3\u5e76\u964d\u4f4e\u654c\u4eba\u62a4\u7532\u548c\u9b54\u6cd5\u6297\u6027\u3002",
            "image": {
                "full": "GGun.png",
                "sprite": "spell2.png",
                "group": "spell",
                "x": 240,
                "y": 48,
                "w": 48,
                "h": 48
            },
            "tooltip": "\u5e93\u5947\u4f7f\u7528\u683c\u6797\u673a\u67aa\u4e0d\u65ad\u626b\u5c04\u524d\u65b9\u7684\u76ee\u6807\uff0c\u6301\u7eed4\u79d2\uff0c\u6700\u591a\u9020\u6210120\/170\/220\/270\/320<scaleAD>(+1.6)<\/scaleAD>\u4f24\u5bb3\uff0c\u5e76\u6700\u591a\u51cf\u5c118\/11\/14\/17\/20\u62a4\u7532\u548c\u9b54\u6cd5\u6297\u6027\u3002<br \/><br \/><span class=\"color919191\"><i>\u683c\u6797\u673a\u67aa\u7684\u4f24\u5bb3\u670950%\u4e3a\u7269\u7406\u4f24\u5bb3\uff0c\u670950%\u4e3a\u9b54\u6cd5\u4f24\u5bb3\u3002<br \/>\u9632\u5fa1\u524a\u51cf\u6548\u679c\u4f1a\u5728\u6700\u540e\u4e00\u6b21\u88ab\u683c\u6797\u673a\u67aa\u51fb\u4e2d\u76842\u79d2\u540e\u6d88\u5931\u3002<\/i><\/span>",
            "leveltip": {
                "label": ["\u4f24\u5bb3", "\u9632\u5fa1\u524a\u51cf"],
                "effect": ["120\/170\/220\/270\/320", "{{ effect4amount*-1.000000 }} -> {{ effect4amountnl*-1.000000 }}"]
            },
            "resource": "50 "
        }, {
            "id": "MissileBarrage",
            "name": "\u706b\u7bad\u8f70\u51fb",
            "description": "\u5e93\u5947\u671d\u7740\u4ed6\u7684\u76ee\u6807\u5730\u70b9\u53d1\u5c04\u4e00\u679a\u5bfc\u5f39\u3002\u5bfc\u5f39\u4f1a\u5728\u547d\u4e2d\u7b2c\u4e00\u4e2a\u654c\u4eba\u540e\u7206\u70b8\uff0c\u5e76\u5bf9\u76ee\u6807\u5468\u56f4\u5355\u4f4d\u9020\u6210\u4f24\u5bb3\u3002\u5e93\u5947\u6bcf\u9694\u4e00\u6bb5\u65f6\u95f4\u5c31\u4f1a\u6302\u8f7d\u4e00\u679a\u5bfc\u5f39\uff0c\u6700\u591a\u6302\u8f7d7\u679a\u3002\u6bcf\u53d1\u5c042\u679a\u5bfc\u5f39\u540e\uff0c\u4e0b\u4e00\u6b21\u5c31\u4f1a\u53d1\u5c04\u8d85\u7ea7\u5bfc\u5f39\uff0c\u9020\u6210\u989d\u5916\u4f24\u5bb3\u3002",
            "image": {
                "full": "MissileBarrage.png",
                "sprite": "spell2.png",
                "group": "spell",
                "x": 288,
                "y": 48,
                "w": 48,
                "h": 48
            },
            "tooltip": "<spellPassive>\u4e3b\u52a8\uff1a<\/spellPassive>\u5e93\u5947\u671d\u76ee\u6807\u5730\u70b9\u53d1\u5c04\u4e00\u679a\u5bfc\u5f39\uff0c\u547d\u4e2d\u7b2c\u4e00\u4e2a\u654c\u4eba\u540e\u4f1a\u7206\u70b8\u3002\u6bcf\u679a\u5bfc\u5f39\u4f1a\u5bf9\u76ee\u6807\u53ca\u76ee\u6807\u5468\u56f4\u7684\u6240\u6709\u654c\u4eba\u9020\u621075\/100\/125<scaleAD>(+10)<\/scaleAD> <scaleAP>(+0.2)<\/scaleAP>\u9b54\u6cd5\u4f24\u5bb3\u3002<br \/><br \/>\u5e93\u5947\u6700\u591a\u53ef\u6302\u8f7d7\u679a\u5bfc\u5f39\u3002\u6bcf\u7b2c3\u679a\u5bfc\u5f39\u5c06\u662f\u8d85\u7ea7\u5bfc\u5f39\uff0c\u9020\u6210%\u4f24\u5bb3 (0)\u3002",
            "leveltip": {
                "label": ["\u4f24\u5bb3", "\u653b\u51fb\u529b\u6536\u76ca", "\u5145\u80fd\u65f6\u95f4"],
                "effect": ["75\/100\/125", "15\/45\/75%", ""]
            },
            "resource": "20 "
        }],
        "passive": {
            "name": "\u6d77\u514b\u65af\u79d1\u6280\u519b\u5907",
            "description": "\u5e93\u5947\u7684\u666e\u901a\u653b\u51fb\u4f1a\u9020\u6210\u4e00\u5b9a\u767e\u5206\u6bd4\u7684\u9b54\u6cd5\u4f24\u5bb3\u800c\u975e\u7269\u7406\u4f24\u5bb3\u3002\u6bcf\u8fc7\u4e00\u6bb5\u65f6\u95f4\uff0c\u3010\u5f39\u836f\u5305\u3011\u5c31\u4f1a\u51fa\u73b0\u5728\u5e93\u5947\u7684\u57fa\u5730\u4e2d\u4ee5\u4f9b\u4ed6\u62fe\u53d6\uff0c\u5e76\u4e14\u4e00\u65e6\u88ab\u62fe\u8d77\uff0c\u5c31\u4f1a\u6682\u65f6\u4e3a\u4ed6\u63d0\u4f9b\u975e\u6218\u6597\u72b6\u6001\u4e0b\u7684\u79fb\u52a8\u901f\u5ea6\u52a0\u6210\u5e76\u5c06\u3010\u74e6\u5c14\u57fa\u91cc\u4fef\u51b2\u3011\u5347\u7ea7\u4e3a\u3010\u7279\u522b\u5feb\u9012\u3011\u3002",
            "image": {
                "full": "Corki_RapidReload.png",
                "sprite": "passive0.png",
                "group": "passive",
                "x": 384,
                "y": 48,
                "w": 48,
                "h": 48
            }
        },
        "lore": "\u7ea6\u5fb7\u5c14\u98de\u884c\u5458\u5e93\u5947\u6700\u7231\u4e24\u4ef6\u4e8b\u7269\uff1a\u4e00\u662f\u98de\u884c\uff0c\u4e8c\u662f\u81ea\u5df1\u82f1\u4fca\u7684\u5c0f\u80e1\u5b50\uff0c\u6392\u540d\u4e0d\u5206\u5148\u540e\u3002\u79bb\u5f00\u73ed\u5fb7\u5c14\u57ce\u4ee5\u540e\uff0c\u4ed6\u5728\u76ae\u5c14\u7279\u6c83\u592b\u5b89\u5bb6\uff0c\u4ece\u6b64\u7231\u4e0a\u4e86\u8fd9\u91cc\u5947\u5f02\u58ee\u89c2\u7684\u5404\u5f0f\u673a\u5668\u3002\u4ed6\u51b3\u5b9a\u6295\u8eab\u4e8e\u98de\u884c\u88c5\u7f6e\u7684\u5f00\u53d1\u4e8b\u4e1a\uff0c\u5e26\u9886\u4e00\u7fa4\u8001\u7ec3\u98de\u884c\u5458\u7ec4\u6210\u4e86\u4e00\u53ea\u7a7a\u4e2d\u9632\u5fa1\u529b\u91cf\uff0c\u5f97\u540d\u201c\u5c16\u5578\u4e4b\u86c7\u201d\u3002\u4e34\u5371\u4e0d\u4e71\u7684\u5e93\u5947\u5728\u4ed6\u7b2c\u4e8c\u6545\u4e61\u7684\u7a7a\u57df\u6212\u5907\u5de1\u903b\uff0c\u800c\u4ed6\u8fd8\u4ece\u6ca1\u89c1\u8fc7\u4ec0\u4e48\u95ee\u9898\u662f\u4e00\u8f6e\u5bfc\u5f39\u9f50\u5c04\u4e0d\u80fd\u89e3\u51b3\u7684\u3002",
        "blurb": "\u7ea6\u5fb7\u5c14\u98de\u884c\u5458\u5e93\u5947\u6700\u7231\u4e24\u4ef6\u4e8b\u7269\uff1a\u4e00\u662f\u98de\u884c\uff0c\u4e8c\u662f\u81ea\u5df1\u82f1\u4fca\u7684\u5c0f\u80e1\u5b50\uff0c\u6392\u540d\u4e0d\u5206\u5148\u540e\u3002\u79bb\u5f00\u73ed\u5fb7\u5c14\u57ce\u4ee5\u540e\uff0c\u4ed6\u5728\u76ae\u5c14\u7279\u6c83\u592b\u5b89\u5bb6\uff0c\u4ece\u6b64\u7231\u4e0a\u4e86\u8fd9\u91cc\u5947\u5f02\u58ee\u89c2\u7684\u5404\u5f0f\u673a\u5668\u3002\u4ed6\u51b3\u5b9a\u6295\u8eab\u4e8e\u98de\u884c\u88c5\u7f6e\u7684\u5f00\u53d1\u4e8b\u4e1a\uff0c\u5e26\u9886\u4e00\u7fa4\u8001\u7ec3\u98de\u884c\u5458\u7ec4\u6210\u4e86\u4e00\u53ea\u7a7a\u4e2d\u9632\u5fa1\u529b\u91cf\uff0c\u5f97\u540d\u201c\u5c16\u5578\u4e4b\u86c7\u201d\u3002\u4e34\u5371\u4e0d\u4e71\u7684\u5e93\u5947\u5728\u4ed6\u7b2c\u4e8c\u6545\u4e61\u7684\u7a7a\u57df\u6212\u5907\u5de1\u903b\uff0c\u800c\u4ed6\u8fd8\u4ece\u6ca1\u89c1\u8fc7\u4ec0\u4e48\u95ee\u9898\u662f\u4e00\u8f6e\u5bfc\u5f39\u9f50\u5c04\u4e0d\u80fd\u89e3\u51b3\u7684\u3002",
        "allytips": ["- \u78f7\u5149\u70b8\u5f39\u53ef\u4ee5\u7528\u6765\u66b4\u9732\u8eb2\u5728\u9644\u8fd1\u8349\u4e1b\u91cc\u7684\u654c\u4eba\u3002", "- \u74e6\u5c14\u57fa\u91cc\u4fef\u51b2\u4e5f\u53ef\u7528\u6765\u9632\u8eab\uff0c\u8bd5\u7740\u7528\u5b83\u6765\u5feb\u901f\u9003\u8131\u3002", "- \u5e93\u5947\u5728\u4f7f\u7528\u683c\u6797\u673a\u67aa\u65f6\u4e5f\u80fd\u8fdb\u884c\u666e\u901a\u653b\u51fb\u3002\u5c06\u683c\u6797\u673a\u67aa\u7684\u4f24\u5bb3\u6700\u5927\u5316\u662f\u638c\u63e1\u5e93\u5947\u7684\u5173\u952e\u3002"],
        "enemytips": ["- \u5c0f\u5fc3\u5e93\u5947\u7684\u78f7\u5149\u70b8\u5f39\u7684\u6e85\u5c04\u4f24\u5bb3\uff0c\u5c31\u7b97\u4f60\u8eb2\u5728\u5c0f\u5175\u540e\u9762\u4e5f\u4f1a\u53d7\u4f24\u3002", "- \u5e93\u5947\u4f7f\u7528\u3010\u74e6\u5c14\u57fa\u91cc\u4fef\u51b2\u3011\u6216\u3010\u7279\u522b\u5feb\u9012\u3011\u540e\u53d8\u5f97\u8106\u5f31\u3002\u82e5\u5e93\u5947\u7528\u8fd9\u4e9b\u6280\u80fd\u6765\u5207\u5165\u6218\u573a\uff0c\u5219\u53ef\u628a\u6ce8\u610f\u529b\u8f6c\u79fb\u5230\u4ed6\u8eab\u4e0a\u3002"],
        "blocks": [{
            "map": "12",
            "mode": "ARAM",
            "recommended": [{
                "type": "starting",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "SummonerSmite",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3044", "count": 1, "hideCount": false}, {
                    "id": "2003",
                    "count": 3,
                    "hideCount": false
                }]
            }, {
                "type": "essential",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3078", "count": 1, "hideCount": false}, {
                    "id": "3020",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3031", "count": 1, "hideCount": false}]
            }, {
                "type": "offensive",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3508", "count": 1, "hideCount": false}, {
                    "id": "3094",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3135", "count": 1, "hideCount": false}]
            }, {
                "type": "defensive",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3072", "count": 1, "hideCount": false}, {
                    "id": "3139",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3156", "count": 1, "hideCount": false}]
            }, {
                "type": "offmeta",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3008", "count": 1, "hideCount": false}, {
                    "id": "3007",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3124", "count": 1, "hideCount": false}]
            }, {
                "type": "consumables",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "2003", "count": 1, "hideCount": false}, {
                    "id": "2140",
                    "count": 1,
                    "hideCount": false
                }, {"id": "2033", "count": 1, "hideCount": false}]
            }]
        }, {
            "map": "1",
            "mode": "CLASSIC",
            "recommended": [{
                "type": "starting",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "1055", "count": 1, "hideCount": false}, {
                    "id": "2003",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3340", "count": 1, "hideCount": false}]
            }, {
                "type": "early",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3057", "count": 1, "hideCount": false}, {
                    "id": "3044",
                    "count": 1,
                    "hideCount": false
                }, {"id": "1001", "count": 1, "hideCount": false}]
            }, {
                "type": "essential",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3078", "count": 1, "hideCount": false}, {
                    "id": "3020",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3094", "count": 1, "hideCount": false}]
            }, {
                "type": "offensive",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3031", "count": 1, "hideCount": false}, {
                    "id": "3072",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3508", "count": 1, "hideCount": false}]
            }, {
                "type": "situational",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "3026", "count": 1, "hideCount": false}, {
                    "id": "3135",
                    "count": 1,
                    "hideCount": false
                }, {"id": "3139", "count": 1, "hideCount": false}]
            }, {
                "type": "consumables",
                "recMath": false,
                "recSteps": false,
                "minSummonerLevel": -1,
                "maxSummonerLevel": -1,
                "showIfSummonerSpell": "",
                "hideIfSummonerSpell": "",
                "appendAfterSection": "",
                "visibleWithAllOf": [""],
                "hiddenWithAnyOf": [""],
                "items": [{"id": "2003", "count": 1, "hideCount": true}, {"id": "2140", "count": 1, "hideCount": true}]
            }]
        }]
    }, "version": "8.21.1", "updated": "2018-10-24"
};